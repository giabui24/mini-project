import type {Meta, StoryObj} from '@storybook/react-vite';

import {StaticStatistics} from './Statistics';

const meta = {
    component: StaticStatistics,
    tags: ['autodocs'],
    title: 'Organisms/Statistics',
} satisfies Meta<typeof StaticStatistics>;

export default meta;

type Story = StoryObj<typeof meta>;

export const Default: Story = {};
