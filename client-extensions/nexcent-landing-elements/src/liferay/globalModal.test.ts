import {afterEach, describe, expect, it, vi} from 'vitest';

import {
    findModalTrigger,
    GLOBAL_MODAL_OPEN_EVENT,
    installModalTriggerDelegation,
    openGlobalModal,
    parseModalDocumentRule,
    resolveModalDocument,
    subscribeGlobalModalOpen,
    type ResolvedModalDocument,
} from './globalModal';

function createElement({
    attributes = {},
    currentSrc = '',
    src = '',
    tagName = 'SPAN',
    textContent = '',
}: {
    attributes?: Record<string, string>;
    currentSrc?: string;
    src?: string;
    tagName?: string;
    textContent?: string;
}) {
    return {
        currentSrc,
        getAttribute: (name: string) => attributes[name] ?? null,
        src,
        tagName,
        textContent,
    } as unknown as Element;
}

function createTrigger({
    modalData = '',
    selectors = {},
}: {
    modalData?: string;
    selectors?: Record<string, Element>;
}) {
    return {
        getAttribute: (name: string) =>
            name === 'data-nxc-modal' ? modalData : null,
        hasAttribute: (name: string) =>
            name === 'data-nxc-modal' && Boolean(modalData),
        matches: () => false,
        querySelector: (selector: string) => selectors[selector] ?? null,
    } as unknown as HTMLElement;
}

const RESOLVED_DOCUMENT: ResolvedModalDocument = {
    id: 'members',
    slots: {
        primaryValue: '2,245,341',
        title: 'Members',
    },
    version: 1,
};

afterEach(() => {
    vi.unstubAllGlobals();
});

describe('global modal document schema', () => {
    it('parses a valid versioned rule and rejects malformed input', () => {
        expect(
            parseModalDocumentRule(
                JSON.stringify({
                    slots: {title: {value: 'Members'}},
                    version: 1,
                })
            )
        ).toMatchObject({version: 1});
        expect(parseModalDocumentRule('{')).toBeNull();
        expect(
            parseModalDocumentRule(
                JSON.stringify({slots: {}, version: 1})
            )
        ).toBeNull();
        expect(
            parseModalDocumentRule(
                JSON.stringify({
                    slots: {title: {value: 'Members'}},
                    version: 2,
                })
            )
        ).toBeNull();
    });

    it('resolves direct values, current text, images, and facts', () => {
        const rule = parseModalDocumentRule(
            JSON.stringify({
                id: 'members',
                slots: {
                    facts: [
                        {
                            label: {value: 'Updated'},
                            value: {read: 'text', selector: '.date'},
                        },
                    ],
                    media: {read: 'image', selector: 'img'},
                    primaryValue: {
                        read: 'text',
                        selector: '.value',
                    },
                    title: {read: 'text', selector: '.label'},
                },
                version: 1,
            })
        );
        const trigger = createTrigger({
            selectors: {
                '.date': createElement({textContent: '29 July 2026'}),
                '.label': createElement({textContent: ' Members '}),
                '.value': createElement({textContent: ' 2,245,341 '}),
                img: createElement({
                    attributes: {alt: 'Members'},
                    currentSrc: '/members.svg',
                    tagName: 'IMG',
                }),
            },
        });

        expect(rule && resolveModalDocument(rule, trigger)).toEqual({
            id: 'members',
            slots: {
                facts: [{label: 'Updated', value: '29 July 2026'}],
                media: {alt: 'Members', url: '/members.svg'},
                primaryValue: '2,245,341',
                title: 'Members',
            },
            version: 1,
        });
    });

    it('rejects a resolved document when its required title is missing', () => {
        const rule = parseModalDocumentRule(
            JSON.stringify({
                slots: {
                    description: {
                        read: 'text',
                        selector: '.missing',
                    },
                    title: {read: 'text', selector: '.missing'},
                },
                version: 1,
            })
        );

        expect(
            rule && resolveModalDocument(rule, createTrigger({}))
        ).toBeNull();
    });
});

describe('global modal trigger delegation', () => {
    it('finds a declarative trigger in a composed event path', () => {
        const trigger = createTrigger({
            modalData: JSON.stringify({
                slots: {title: {value: 'Members'}},
                version: 1,
            }),
        });

        expect(findModalTrigger([{} as EventTarget, trigger])).toBe(trigger);
    });

    it('resolves the trigger document when a delegated click occurs', () => {
        let clickListener: ((event: MouseEvent) => void) | undefined;
        const documentTarget = {
            addEventListener: (
                name: string,
                listener: (event: MouseEvent) => void
            ) => {
                if (name === 'click') {
                    clickListener = listener;
                }
            },
            removeEventListener: vi.fn(),
        };
        const trigger = createTrigger({
            modalData: JSON.stringify({
                id: 'members',
                slots: {
                    primaryValue: {value: '2,245,341'},
                    title: {value: 'Members'},
                },
                version: 1,
            }),
        });
        const handler = vi.fn();

        vi.stubGlobal('window', {document: documentTarget});

        const uninstall = installModalTriggerDelegation(handler);

        clickListener?.({
            composedPath: () => [trigger],
        } as unknown as MouseEvent);

        expect(handler).toHaveBeenCalledWith(RESOLVED_DOCUMENT, trigger);

        uninstall();
        expect(documentTarget.removeEventListener).toHaveBeenCalledWith(
            'click',
            clickListener
        );
    });
});

describe('global modal EventBus', () => {
    it('uses Liferay.fire/on and detaches its subscription', () => {
        const detach = vi.fn();
        const fire = vi.fn();
        let liferayListener: ((payload: unknown) => void) | undefined;
        const on = vi.fn(
            (_eventName: string, listener: (payload: unknown) => void) => {
                liferayListener = listener;
                return {detach};
            }
        );

        vi.stubGlobal('window', {Liferay: {fire, on}});

        const listener = vi.fn();
        const unsubscribe = subscribeGlobalModalOpen(listener);

        openGlobalModal(RESOLVED_DOCUMENT);
        liferayListener?.({version: 2});
        liferayListener?.(RESOLVED_DOCUMENT);

        expect(fire).toHaveBeenCalledWith(
            GLOBAL_MODAL_OPEN_EVENT,
            RESOLVED_DOCUMENT
        );
        expect(listener).toHaveBeenCalledWith(RESOLVED_DOCUMENT);
        expect(listener).toHaveBeenCalledTimes(1);

        unsubscribe();
        expect(detach).toHaveBeenCalledOnce();
    });

    it('falls back to native custom events outside Liferay', () => {
        class TestCustomEvent<T> extends Event {
            detail: T;

            constructor(name: string, init: {detail: T}) {
                super(name);
                this.detail = init.detail;
            }
        }

        const eventTarget = new EventTarget();
        const fakeWindow = {
            addEventListener: eventTarget.addEventListener.bind(eventTarget),
            dispatchEvent: eventTarget.dispatchEvent.bind(eventTarget),
            removeEventListener:
                eventTarget.removeEventListener.bind(eventTarget),
        };

        vi.stubGlobal('CustomEvent', TestCustomEvent);
        vi.stubGlobal('window', fakeWindow);

        const listener = vi.fn();
        const unsubscribe = subscribeGlobalModalOpen(listener);

        openGlobalModal(RESOLVED_DOCUMENT);

        expect(listener).toHaveBeenCalledWith(RESOLVED_DOCUMENT);

        unsubscribe();
    });
});
