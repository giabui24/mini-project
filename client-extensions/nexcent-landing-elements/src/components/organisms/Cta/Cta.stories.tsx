import type {Meta, StoryObj} from '@storybook/react-vite';

import {StaticCta} from './Cta';

const meta = {
    component: StaticCta,
    tags: ['autodocs'],
    title: 'Organisms/CTA',
} satisfies Meta<typeof StaticCta>;

export default meta;

type Story = StoryObj<typeof meta>;

export const Default: Story = {};
