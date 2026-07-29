import type {Meta, StoryObj} from '@storybook/react-vite';

import {StaticMarketing} from './Marketing';

const meta = {
    component: StaticMarketing,
    tags: ['autodocs'],
    title: 'Landing Elements/Marketing',
} satisfies Meta<typeof StaticMarketing>;

export default meta;

type Story = StoryObj<typeof meta>;

export const Default: Story = {};
