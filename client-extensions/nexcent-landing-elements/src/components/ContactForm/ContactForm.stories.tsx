import type {Meta, StoryObj} from '@storybook/react-vite';

import {ContactForm} from './ContactForm';

const meta = {
    args: {
        apiPath: '/o/c/nxccontactrequests',
        description:
            'Tell us what you are working on and our team will get back to you.',
        errorMessage: 'We could not send your message. Please try again.',
        submitLabel: 'Submit',
        submittingText: 'Sending…',
        successMessage: 'Thanks! Your message has been sent.',
        title: 'Contact us',
    },
    argTypes: {
        apiPath: {
            control: 'text',
            description: 'Same-origin Liferay API endpoint.',
        },
        description: {control: 'text'},
        errorMessage: {control: 'text'},
        host: {table: {disable: true}},
        submitLabel: {control: 'text'},
        submittingText: {control: 'text'},
        successMessage: {control: 'text'},
        title: {control: 'text'},
    },
    component: ContactForm,
    parameters: {
        docs: {
            description: {
                component:
                    'Production Contact Us form used by the Nexcent Liferay Fragment. Use Controls to preview the Fragment configuration values.',
            },
        },
    },
    tags: ['autodocs'],
    title: 'Components/Contact Form',
} satisfies Meta<typeof ContactForm>;

export default meta;

type Story = StoryObj<typeof meta>;

export const Default: Story = {};

export const LongContent: Story = {
    args: {
        description:
            'Share your project goals, timeline, platform requirements, and any integration constraints. Our team will review the details and contact you.',
        title: 'Build your next digital experience with Nexcent',
    },
};
