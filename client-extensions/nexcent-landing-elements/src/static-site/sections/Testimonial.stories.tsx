import type {Meta, StoryObj} from '@storybook/react-vite';

import {StaticTestimonial} from './Testimonial';

const meta = {
    component: StaticTestimonial,
    tags: ['autodocs'],
    title: 'Landing Elements/Testimonial',
} satisfies Meta<typeof StaticTestimonial>;

export default meta;

type Story = StoryObj<typeof meta>;

export const Default: Story = {};
