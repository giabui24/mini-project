import {useCallback, useEffect, useId, useRef, useState} from 'react';

import {
    closeGlobalModal,
    installModalTriggerDelegation,
    openGlobalModal,
    type ResolvedModalDocument,
    subscribeGlobalModalOpen,
} from '../../liferay/globalModal';

import modalCss from './global-modal.css?inline';

export function GlobalModal() {
    const headingId = useId();
    const closeButtonRef = useRef<HTMLButtonElement>(null);
    const returnFocusRef = useRef<HTMLElement | null>(null);
    const [document, setDocument] = useState<ResolvedModalDocument | null>(
        null
    );

    useEffect(() => {
        const unsubscribe = subscribeGlobalModalOpen((nextDocument) => {
            if (!returnFocusRef.current) {
                returnFocusRef.current =
                    window.document.activeElement instanceof HTMLElement
                        ? window.document.activeElement
                        : null;
            }

            setDocument(nextDocument);
        });
        const uninstallDelegation = installModalTriggerDelegation(
            (nextDocument, trigger) => {
                returnFocusRef.current = trigger;
                openGlobalModal(nextDocument);
            }
        );

        return () => {
            uninstallDelegation();
            unsubscribe();
        };
    }, []);

    const close = useCallback(
        (reason: 'backdrop' | 'button' | 'escape') => {
            if (!document) {
                return;
            }

            closeGlobalModal({
                ...(document.id ? {id: document.id} : {}),
                reason,
            });
            setDocument(null);

            window.requestAnimationFrame(() => {
                returnFocusRef.current?.focus();
                returnFocusRef.current = null;
            });
        },
        [document]
    );

    useEffect(() => {
        if (!document) {
            return;
        }

        const previousOverflow = window.document.body.style.overflow;
        const onKeyDown = (event: KeyboardEvent) => {
            if (event.key === 'Escape') {
                event.preventDefault();
                close('escape');
            }
            else if (event.key === 'Tab') {
                event.preventDefault();
                closeButtonRef.current?.focus();
            }
        };

        window.document.body.style.overflow = 'hidden';
        window.document.addEventListener('keydown', onKeyDown);
        window.requestAnimationFrame(() => closeButtonRef.current?.focus());

        return () => {
            window.document.body.style.overflow = previousOverflow;
            window.document.removeEventListener('keydown', onKeyDown);
        };
    }, [close, document]);

    if (!document) {
        return <style>{modalCss}</style>;
    }

    const {slots} = document;

    return (
        <>
            <style>{modalCss}</style>
            <div
                className="nxc-global-modal__backdrop"
                onMouseDown={(event) => {
                    if (event.target === event.currentTarget) {
                        close('backdrop');
                    }
                }}
            >
                <section
                    aria-labelledby={headingId}
                    aria-modal="true"
                    className={`nxc-global-modal${
                        slots.media
                            ? ''
                            : ' nxc-global-modal--without-media'
                    }`}
                    role="dialog"
                >
                    <button
                        aria-label="Close details"
                        className="nxc-global-modal__close"
                        onClick={() => close('button')}
                        ref={closeButtonRef}
                        type="button"
                    >
                        <span aria-hidden="true">{'\u00d7'}</span>
                    </button>

                    {slots.media ? (
                        <div className="nxc-global-modal__media">
                            <img
                                alt={slots.media.alt}
                                src={slots.media.url}
                            />
                        </div>
                    ) : null}

                    <div className="nxc-global-modal__content">
                        {slots.eyebrow ? (
                            <p className="nxc-global-modal__eyebrow">
                                {slots.eyebrow}
                            </p>
                        ) : null}

                        <h2 id={headingId}>{slots.title}</h2>

                        {slots.primaryValue ? (
                            <p className="nxc-global-modal__primary-value">
                                {slots.primaryValue}
                            </p>
                        ) : null}

                        {slots.description ? (
                            <p className="nxc-global-modal__description">
                                {slots.description}
                            </p>
                        ) : null}

                        {slots.facts?.length ? (
                            <dl className="nxc-global-modal__facts">
                                {slots.facts.map((fact, index) => (
                                    <div key={`${fact.label}-${index}`}>
                                        <dt>{fact.label}</dt>
                                        <dd>{fact.value}</dd>
                                    </div>
                                ))}
                            </dl>
                        ) : null}
                    </div>
                </section>
            </div>
        </>
    );
}
