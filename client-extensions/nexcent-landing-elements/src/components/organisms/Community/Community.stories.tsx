import type {Meta, StoryObj} from '@storybook/react-vite';

import {StaticCommunity} from './Community';

const meta = {
    component: StaticCommunity,
    tags: ['autodocs'],
    title: 'Organisms/Community',
} satisfies Meta<typeof StaticCommunity>;

export default meta;

type Story = StoryObj<typeof meta>;

export const Default: Story = {};
