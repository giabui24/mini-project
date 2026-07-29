import type {Meta, StoryObj} from '@storybook/react-vite';

import {StaticFeature} from './Feature';

const meta = {
    args: {
        featureKey: 'primary',
    },
    component: StaticFeature,
    tags: ['autodocs'],
    title: 'Landing Elements/Feature',
} satisfies Meta<typeof StaticFeature>;

export default meta;

type Story = StoryObj<typeof meta>;

export const Primary: Story = {};

export const Secondary: Story = {
    args: {
        featureKey: 'secondary',
    },
};
