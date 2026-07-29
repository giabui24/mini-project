import type {Meta, StoryObj} from '@storybook/react-vite';

import {StaticFooter} from './Footer';

const meta = {
    component: StaticFooter,
    tags: ['autodocs'],
    title: 'Landing Elements/Footer',
} satisfies Meta<typeof StaticFooter>;

export default meta;

type Story = StoryObj<typeof meta>;

export const Default: Story = {};
