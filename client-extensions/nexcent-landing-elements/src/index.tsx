import React, {type ReactNode} from 'react';
import {createRoot, type Root} from 'react-dom/client';

import {ContactForm} from './components/ContactForm/ContactForm';
import {registerStaticElements} from './landing/registerStaticElements';

type ElementRenderer = (element: HTMLElement) => ReactNode;

function registerReactElement(name: string, renderer: ElementRenderer) {
    if (customElements.get(name)) {
        return;
    }

    class LiferayReactElement extends HTMLElement {
        private root?: Root;

        connectedCallback() {
            if (this.root) {
                return;
            }

            this.root = createRoot(this);
            this.root.render(
                <React.StrictMode>{renderer(this)}</React.StrictMode>
            );
        }

        disconnectedCallback() {
            this.root?.unmount();
            this.root = undefined;
        }
    }

    customElements.define(name, LiferayReactElement);
}

registerReactElement('nexcent-contact-form', (element) => (
    <ContactForm host={element} />
));

registerStaticElements();
