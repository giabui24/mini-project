import type {Meta, StoryObj} from '@storybook/react-vite';

import {StaticClients} from './Clients';

const meta = {
    component: StaticClients,
    tags: ['autodocs'],
    title: 'Landing Elements/Clients',
} satisfies Meta<typeof StaticClients>;

export default meta;

type Story = StoryObj<typeof meta>;

export const Default: Story = {};
