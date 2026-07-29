import type {Preview} from '@storybook/react-vite';
import {createElement} from 'react';

import {StaticRuntimeOverrides} from '../src/components/Landing/shell/StaticRuntimeOverrides';
import {StaticStyleBoundary} from '../src/components/Landing/shell/StaticStyleBoundary';

const preview: Preview = {
    decorators: [
        (Story) =>
            createElement(
                StaticStyleBoundary,
                null,
                createElement(
                    StaticRuntimeOverrides,
                    null,
                    createElement(Story)
                )
            ),
    ],
    parameters: {
        a11y: {
            test: 'todo',
        },
        controls: {
            matchers: {
                color: /(background|color)$/i,
                date: /Date$/i,
            },
        },
        layout: 'fullscreen',
    },
};

export default preview;
