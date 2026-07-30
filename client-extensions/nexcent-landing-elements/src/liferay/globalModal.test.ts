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
    modalEditing = false,
    selectors = {},
}: {
    modalData?: string;
    modalEditing?: boolean;
    selectors?: Record<string, Element>;
}) {
    return {
        getAttribute: (name: string) =>
            name === 'data-nxc-modal' ? modalData : null,
        hasAttribute: (name: string) =>
            (name === 'data-nxc-modal' && Boolean(modalData)) ||
            (name === 'data-nxc-modal-editing' && modalEditing),
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
    vi.restoreAllMocks();
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

    it('resolves a document composed entirely from direct values', () => {
        const rule = parseModalDocumentRule(
            JSON.stringify({
                id: 'featured-customer-story',
                slots: {
                    description: {value: 'A customer success story.'},
                    eyebrow: {value: 'Customer story'},
                    facts: [
                        {
                            label: {value: 'Customer'},
                            value: {value: 'Tim Smith'},
                        },
                    ],
                    media: {
                        alt: 'Tim Smith',
                        url: '/customers/tim-smith.jpg',
                    },
                    title: {
                        value: 'British Dragon Boat Racing Association',
                    },
                },
                version: 1,
            })
        );

        expect(
            rule && resolveModalDocument(rule, createTrigger({}))
        ).toEqual({
            id: 'featured-customer-story',
            slots: {
                description: 'A customer success story.',
                eyebrow: 'Customer story',
                facts: [{label: 'Customer', value: 'Tim Smith'}],
                media: {
                    alt: 'Tim Smith',
                    url: '/customers/tim-smith.jpg',
                },
                title: 'British Dragon Boat Racing Association',
            },
            version: 1,
        });
    });

    it.each([
        {
            current: '1,100',
            expected: {
                deltaValue: '100',
                direction: 'up',
                percent: 10,
                previousValue: '1000',
            },
            previous: '1,000',
        },
        {
            current: '900',
            expected: {
                deltaValue: '-100',
                direction: 'down',
                percent: -10,
                previousValue: '1000',
            },
            previous: '1,000',
        },
        {
            current: '1,000',
            expected: {
                deltaValue: '0',
                direction: 'neutral',
                percent: 0,
                previousValue: '1000',
            },
            previous: '1,000',
        },
        {
            current: '10',
            expected: {
                deltaValue: '10',
                direction: 'up',
                previousValue: '0',
            },
            previous: '0',
        },
    ])(
        'resolves $expected.direction metric comparisons',
        ({current, expected, previous}) => {
            const rule = parseModalDocumentRule(
                JSON.stringify({
                    slots: {
                        comparison: {
                            current: {value: current},
                            previous: {value: previous},
                        },
                        title: {value: 'Members'},
                    },
                    version: 1,
                })
            );

            expect(
                rule &&
                    resolveModalDocument(rule, createTrigger({}))?.slots
                        .comparison
            ).toEqual(expected);
        }
    );

    it('omits a comparison when either value is not an integer', () => {
        const rule = parseModalDocumentRule(
            JSON.stringify({
                slots: {
                    comparison: {
                        current: {value: '1,000'},
                        previous: {value: 'Not available'},
                    },
                    title: {value: 'Members'},
                },
                version: 1,
            })
        );

        expect(
            rule && resolveModalDocument(rule, createTrigger({}))
        ).toEqual({
            slots: {title: 'Members'},
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
        const preventDefault = vi.fn();

        vi.stubGlobal('window', {document: documentTarget});

        const uninstall = installModalTriggerDelegation(handler);

        clickListener?.({
            altKey: false,
            button: 0,
            composedPath: () => [trigger],
            ctrlKey: false,
            defaultPrevented: false,
            metaKey: false,
            preventDefault,
            shiftKey: false,
        } as unknown as MouseEvent);

        expect(handler).toHaveBeenCalledWith(RESOLVED_DOCUMENT, trigger);
        expect(preventDefault).toHaveBeenCalledOnce();

        uninstall();
        expect(documentTarget.removeEventListener).toHaveBeenCalledWith(
            'click',
            clickListener
        );
    });

    it('does not block navigation when the trigger rule is invalid', () => {
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
        const trigger = createTrigger({modalData: '{'});
        const handler = vi.fn();
        const preventDefault = vi.fn();

        vi.stubGlobal('window', {document: documentTarget});
        vi.spyOn(console, 'warn').mockImplementation(() => undefined);

        const uninstall = installModalTriggerDelegation(handler);

        clickListener?.({
            altKey: false,
            button: 0,
            composedPath: () => [trigger],
            ctrlKey: false,
            defaultPrevented: false,
            metaKey: false,
            preventDefault,
            shiftKey: false,
        } as unknown as MouseEvent);

        expect(handler).not.toHaveBeenCalled();
        expect(preventDefault).not.toHaveBeenCalled();

        uninstall();
    });

    it.each([
        {button: 1, name: 'a non-primary click'},
        {button: 0, ctrlKey: true, name: 'a modified click'},
        {button: 0, defaultPrevented: true, name: 'an already handled click'},
    ])('keeps fallback navigation for $name', (eventState) => {
        let clickListener: ((event: MouseEvent) => void) | undefined;
        const documentTarget = {
            addEventListener: vi.fn(
                (_name: string, listener: (event: MouseEvent) => void) => {
                    clickListener = listener;
                }
            ),
            removeEventListener: vi.fn(),
        };
        const trigger = createTrigger({
            modalData: JSON.stringify({
                slots: {title: {value: 'Members'}},
                version: 1,
            }),
        });
        const handler = vi.fn();
        const preventDefault = vi.fn();

        vi.stubGlobal('window', {document: documentTarget});

        const uninstall = installModalTriggerDelegation(handler);

        clickListener?.(
            Object.assign(
                {
                    altKey: false,
                    button: 0,
                    composedPath: () => [trigger],
                    ctrlKey: false,
                    defaultPrevented: false,
                    metaKey: false,
                    preventDefault,
                    shiftKey: false,
                },
                eventState
            ) as unknown as MouseEvent
        );

        expect(handler).not.toHaveBeenCalled();
        expect(preventDefault).not.toHaveBeenCalled();

        uninstall();
    });

    it('ignores modal triggers while their fragment is being edited', () => {
        let clickListener: ((event: MouseEvent) => void) | undefined;
        const documentTarget = {
            addEventListener: vi.fn(
                (_name: string, listener: (event: MouseEvent) => void) => {
                    clickListener = listener;
                }
            ),
            removeEventListener: vi.fn(),
        };
        const trigger = createTrigger({
            modalData: JSON.stringify({
                slots: {title: {value: 'Members'}},
                version: 1,
            }),
            modalEditing: true,
        });
        const handler = vi.fn();
        const preventDefault = vi.fn();

        vi.stubGlobal('window', {document: documentTarget});

        const uninstall = installModalTriggerDelegation(handler);

        clickListener?.({
            altKey: false,
            button: 0,
            composedPath: () => [trigger],
            ctrlKey: false,
            defaultPrevented: false,
            metaKey: false,
            preventDefault,
            shiftKey: false,
        } as unknown as MouseEvent);

        expect(handler).not.toHaveBeenCalled();
        expect(preventDefault).not.toHaveBeenCalled();

        uninstall();
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
