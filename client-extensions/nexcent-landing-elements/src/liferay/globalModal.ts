import {getLiferay} from './global';

export const GLOBAL_MODAL_CLOSED_EVENT = 'nexcent:modal:closed';
export const GLOBAL_MODAL_OPEN_EVENT = 'nexcent:modal:open';

type DirectImageSource = {
    alt?: string;
    url: string;
};

type DirectTextSource = {
    value: string;
};

type ImageSelectorSource = {
    read: 'image';
    selector: string;
};

type TextSelectorSource = {
    read: 'text';
    selector: string;
};

export type ModalImageSource = DirectImageSource | ImageSelectorSource;
export type ModalTextSource = DirectTextSource | TextSelectorSource;

export type ModalDocumentRule = {
    id?: string;
    slots: {
        description?: ModalTextSource;
        eyebrow?: ModalTextSource;
        facts?: Array<{
            label: ModalTextSource;
            value: ModalTextSource;
        }>;
        media?: ModalImageSource;
        primaryValue?: ModalTextSource;
        title: ModalTextSource;
    };
    version: 1;
};

export type ResolvedModalDocument = {
    id?: string;
    slots: {
        description?: string;
        eyebrow?: string;
        facts?: Array<{
            label: string;
            value: string;
        }>;
        media?: {
            alt: string;
            url: string;
        };
        primaryValue?: string;
        title: string;
    };
    version: 1;
};

export type ModalClosedPayload = {
    id?: string;
    reason: 'backdrop' | 'button' | 'escape';
};

type ModalTriggerHandler = (
    document: ResolvedModalDocument,
    trigger: HTMLElement
) => void;

function isRecord(value: unknown): value is Record<string, unknown> {
    return typeof value === 'object' && value !== null && !Array.isArray(value);
}

function isTextSource(value: unknown): value is ModalTextSource {
    if (!isRecord(value)) {
        return false;
    }

    if (typeof value.value === 'string') {
        return true;
    }

    return value.read === 'text' && typeof value.selector === 'string';
}

function isImageSource(value: unknown): value is ModalImageSource {
    if (!isRecord(value)) {
        return false;
    }

    if (typeof value.url === 'string') {
        return value.alt === undefined || typeof value.alt === 'string';
    }

    return value.read === 'image' && typeof value.selector === 'string';
}

export function parseModalDocumentRule(
    rawValue: string
): ModalDocumentRule | null {
    let value: unknown;

    try {
        value = JSON.parse(rawValue);
    }
    catch {
        return null;
    }

    if (!isRecord(value) || value.version !== 1 || !isRecord(value.slots)) {
        return null;
    }

    const {slots} = value;

    if (!isTextSource(slots.title)) {
        return null;
    }

    for (const slotName of [
        'description',
        'eyebrow',
        'primaryValue',
    ] as const) {
        if (
            slots[slotName] !== undefined &&
            !isTextSource(slots[slotName])
        ) {
            return null;
        }
    }

    if (slots.media !== undefined && !isImageSource(slots.media)) {
        return null;
    }

    if (
        slots.facts !== undefined &&
        (!Array.isArray(slots.facts) ||
            !slots.facts.every(
                (fact) =>
                    isRecord(fact) &&
                    isTextSource(fact.label) &&
                    isTextSource(fact.value)
            ))
    ) {
        return null;
    }

    if (value.id !== undefined && typeof value.id !== 'string') {
        return null;
    }

    return value as ModalDocumentRule;
}

function queryWithinTrigger(
    trigger: HTMLElement,
    selector: string
): Element | null {
    try {
        return trigger.querySelector(selector);
    }
    catch {
        return null;
    }
}

function resolveTextSource(
    source: ModalTextSource | undefined,
    trigger: HTMLElement
): string | undefined {
    if (!source) {
        return undefined;
    }

    const value =
        'value' in source
            ? source.value
            : queryWithinTrigger(trigger, source.selector)?.textContent;
    const normalizedValue = value?.trim();

    return normalizedValue || undefined;
}

function resolveImageSource(
    source: ModalImageSource | undefined,
    trigger: HTMLElement
): {alt: string; url: string} | undefined {
    if (!source) {
        return undefined;
    }

    if ('url' in source) {
        const url = source.url.trim();

        return url ? {alt: source.alt?.trim() || '', url} : undefined;
    }

    const element = queryWithinTrigger(trigger, source.selector);

    if (!element) {
        return undefined;
    }

    const imageElement = element as HTMLImageElement;
    const url =
        element.tagName.toLowerCase() === 'img'
            ? imageElement.currentSrc || imageElement.src
            : element.getAttribute('src') || '';

    if (!url.trim()) {
        return undefined;
    }

    return {
        alt: element.getAttribute('alt')?.trim() || '',
        url: url.trim(),
    };
}

export function resolveModalDocument(
    rule: ModalDocumentRule,
    trigger: HTMLElement
): ResolvedModalDocument | null {
    const title = resolveTextSource(rule.slots.title, trigger);

    if (!title) {
        return null;
    }

    const facts = rule.slots.facts
        ?.map((fact) => ({
            label: resolveTextSource(fact.label, trigger),
            value: resolveTextSource(fact.value, trigger),
        }))
        .filter(
            (fact): fact is {label: string; value: string} =>
                Boolean(fact.label && fact.value)
        );
    const description = resolveTextSource(
        rule.slots.description,
        trigger
    );
    const eyebrow = resolveTextSource(rule.slots.eyebrow, trigger);
    const media = resolveImageSource(rule.slots.media, trigger);
    const primaryValue = resolveTextSource(
        rule.slots.primaryValue,
        trigger
    );

    return {
        ...(rule.id ? {id: rule.id} : {}),
        slots: {
            ...(description ? {description} : {}),
            ...(eyebrow ? {eyebrow} : {}),
            ...(facts?.length ? {facts} : {}),
            ...(media ? {media} : {}),
            ...(primaryValue ? {primaryValue} : {}),
            title,
        },
        version: 1,
    };
}

export function readModalDocument(
    trigger: HTMLElement
): ResolvedModalDocument | null {
    const rawValue = trigger.getAttribute('data-nxc-modal');

    if (!rawValue) {
        return null;
    }

    const rule = parseModalDocumentRule(rawValue);

    return rule ? resolveModalDocument(rule, trigger) : null;
}

export function findModalTrigger(path: EventTarget[]): HTMLElement | null {
    for (const candidate of path) {
        const element = candidate as HTMLElement;

        if (
            typeof element?.hasAttribute === 'function' &&
            element.hasAttribute('data-nxc-modal')
        ) {
            return element;
        }
    }

    return null;
}

export function installModalTriggerDelegation(
    handler: ModalTriggerHandler
): () => void {
    const onClick = (event: MouseEvent) => {
        const trigger = findModalTrigger(event.composedPath());

        if (
            !trigger ||
            trigger.matches(':disabled, [aria-disabled="true"]')
        ) {
            return;
        }

        const document = readModalDocument(trigger);

        if (!document) {
            console.warn(
                '[Nexcent Global Modal] Invalid modal data on trigger.',
                trigger
            );
            return;
        }

        handler(document, trigger);
    };

    window.document.addEventListener('click', onClick);

    return () => window.document.removeEventListener('click', onClick);
}

function emitEvent(eventName: string, payload: unknown) {
    const liferay = getLiferay();

    if (liferay?.fire) {
        liferay.fire(eventName, payload);
        return;
    }

    window.dispatchEvent(new CustomEvent(eventName, {detail: payload}));
}

function subscribeToEvent<T>(
    eventName: string,
    listener: (payload: T) => void
): () => void {
    const liferay = getLiferay();

    if (liferay?.on) {
        const handle = liferay.on(eventName, (payload) =>
            listener(payload as T)
        );

        return () => handle?.detach?.();
    }

    const eventListener = (event: Event) =>
        listener((event as CustomEvent<T>).detail);

    window.addEventListener(eventName, eventListener);

    return () => window.removeEventListener(eventName, eventListener);
}

export function openGlobalModal(document: ResolvedModalDocument) {
    emitEvent(GLOBAL_MODAL_OPEN_EVENT, document);
}

export function closeGlobalModal(payload: ModalClosedPayload) {
    emitEvent(GLOBAL_MODAL_CLOSED_EVENT, payload);
}

export function isResolvedModalDocument(
    value: unknown
): value is ResolvedModalDocument {
    if (
        !isRecord(value) ||
        value.version !== 1 ||
        !isRecord(value.slots) ||
        typeof value.slots.title !== 'string' ||
        !value.slots.title.trim()
    ) {
        return false;
    }

    const {slots} = value;

    for (const slotName of [
        'description',
        'eyebrow',
        'primaryValue',
    ] as const) {
        if (
            slots[slotName] !== undefined &&
            typeof slots[slotName] !== 'string'
        ) {
            return false;
        }
    }

    if (
        slots.media !== undefined &&
        (!isRecord(slots.media) ||
            typeof slots.media.alt !== 'string' ||
            typeof slots.media.url !== 'string')
    ) {
        return false;
    }

    if (
        slots.facts !== undefined &&
        (!Array.isArray(slots.facts) ||
            !slots.facts.every(
                (fact) =>
                    isRecord(fact) &&
                    typeof fact.label === 'string' &&
                    typeof fact.value === 'string'
            ))
    ) {
        return false;
    }

    return value.id === undefined || typeof value.id === 'string';
}

export function subscribeGlobalModalOpen(
    listener: (document: ResolvedModalDocument) => void
) {
    return subscribeToEvent<unknown>(GLOBAL_MODAL_OPEN_EVENT, (payload) => {
        if (isResolvedModalDocument(payload)) {
            listener(payload);
        }
    });
}
