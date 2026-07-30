import {createElement, type ReactNode} from 'react';

import {
    ContactForm,
    type ContactFormProps,
} from './components/ContactForm/ContactForm';
import {getFragmentConfiguration} from './utils/fragment';

export type FragmentComponentMapping = {
    elementName: string;
    render: (host: HTMLElement) => ReactNode;
};

export function mapContactFormProps(host: HTMLElement): ContactFormProps {
    const configuration = getFragmentConfiguration(host);

    return {
        description: configuration.description || '',
        errorMessage: configuration.errorMessage || '',
        submitLabel: configuration.submitLabel || '',
        submittingText: configuration.submittingText || '',
        successMessage: configuration.successMessage || '',
        title: configuration.title || '',
    };
}

export const fragmentComponentMappings: FragmentComponentMapping[] = [
    {
        elementName: 'nexcent-contact-form',
        render: (host) =>
            createElement(ContactForm, mapContactFormProps(host)),
    },
];
