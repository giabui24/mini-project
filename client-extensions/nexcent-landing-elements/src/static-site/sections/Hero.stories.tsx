import type {Meta, StoryObj} from '@storybook/react-vite';

import {StaticHero} from './Hero';

const meta = {
    component: StaticHero,
    tags: ['autodocs'],
    title: 'Landing Elements/Hero',
} satisfies Meta<typeof StaticHero>;

export default meta;

type Story = StoryObj<typeof meta>;

export const Default: Story = {};
