import type {Preview} from '@storybook/react-vite';

import '../src/styles/main.scss';

const preview: Preview = {
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
