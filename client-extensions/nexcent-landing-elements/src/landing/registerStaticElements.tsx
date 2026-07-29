import React, {type ReactNode} from 'react';
import {createRoot, type Root} from 'react-dom/client';

import {StaticClients} from '../components/Clients/Clients';
import {StaticCommunity} from '../components/Community/Community';
import {StaticCta} from '../components/Cta/Cta';
import {StaticFeature} from '../components/Feature/Feature';
import {StaticFooter} from '../components/Footer/Footer';
import {StaticHeader} from '../components/Header/Header';
import {StaticHero} from '../components/Hero/Hero';
import {StaticMarketing} from '../components/Marketing/Marketing';
import {StaticStatistics} from '../components/Statistics/Statistics';
import {StaticTestimonial} from '../components/Testimonial/Testimonial';
import {StaticRuntimeOverrides} from './shell/StaticRuntimeOverrides';
import {StaticStyleBoundary} from './shell/StaticStyleBoundary';

type StaticRenderer = (element: HTMLElement) => ReactNode;

export const staticElementNames = [
    'nexcent-react-header',
    'nexcent-react-hero',
    'nexcent-react-clients',
    'nexcent-react-community',
    'nexcent-react-feature-primary',
    'nexcent-react-statistics',
    'nexcent-react-feature-secondary',
    'nexcent-react-testimonial',
    'nexcent-react-marketing',
    'nexcent-react-cta',
    'nexcent-react-footer',
] as const;

function registerShadowReactElement(name: string, renderer: StaticRenderer) {
    if (customElements.get(name)) {
        return;
    }

    class NexcentStaticReactElement extends HTMLElement {
        private root?: Root;

        connectedCallback() {
            if (this.root) {
                return;
            }

            const shadowRoot =
                this.shadowRoot ?? this.attachShadow({mode: 'open'});

            this.root = createRoot(shadowRoot);
            this.root.render(
                <React.StrictMode>
                    <StaticStyleBoundary>
                        <StaticRuntimeOverrides>
                            {renderer(this)}
                        </StaticRuntimeOverrides>
                    </StaticStyleBoundary>
                </React.StrictMode>
            );
        }

        disconnectedCallback() {
            this.root?.unmount();
            this.root = undefined;
        }
    }

    customElements.define(name, NexcentStaticReactElement);
}

export function registerStaticElements() {
    registerShadowReactElement('nexcent-react-header', (element) => (
        <StaticHeader host={element} />
    ));
    registerShadowReactElement('nexcent-react-hero', (element) => (
        <StaticHero host={element} />
    ));
    registerShadowReactElement('nexcent-react-clients', (element) => (
        <StaticClients host={element} />
    ));
    registerShadowReactElement('nexcent-react-community', (element) => (
        <StaticCommunity host={element} />
    ));
    registerShadowReactElement('nexcent-react-feature-primary', (element) => (
        <StaticFeature featureKey="primary" host={element} />
    ));
    registerShadowReactElement('nexcent-react-statistics', (element) => (
        <StaticStatistics host={element} />
    ));
    registerShadowReactElement('nexcent-react-feature-secondary', (element) => (
        <StaticFeature featureKey="secondary" host={element} />
    ));
    registerShadowReactElement('nexcent-react-testimonial', (element) => (
        <StaticTestimonial host={element} />
    ));
    registerShadowReactElement('nexcent-react-marketing', (element) => (
        <StaticMarketing host={element} />
    ));
    registerShadowReactElement('nexcent-react-cta', (element) => (
        <StaticCta host={element} />
    ));
    registerShadowReactElement('nexcent-react-footer', (element) => (
        <StaticFooter host={element} />
    ));
}
