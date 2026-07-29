import type {Meta, StoryObj} from '@storybook/react-vite';

import {StaticHeader} from './Header';

const meta = {
    component: StaticHeader,
    tags: ['autodocs'],
    title: 'Landing Elements/Header',
} satisfies Meta<typeof StaticHeader>;

export default meta;

type Story = StoryObj<typeof meta>;

export const Default: Story = {};
