import {readFileSync} from 'node:fs';
import path from 'node:path';
import {fileURLToPath} from 'node:url';

import {describe, expect, it} from 'vitest';

const packageDirectory = path.resolve(
    path.dirname(fileURLToPath(import.meta.url)),
    '../../..'
);
const fragmentDirectory = path.join(
    packageDirectory,
    'fragments/nexcent-contact-form'
);

describe('Nexcent Contact Form fragment', () => {
    it('owns the stylesheet required by its light-DOM custom element', () => {
        const definition = JSON.parse(
            readFileSync(path.join(fragmentDirectory, 'fragment.json'), 'utf8')
        ) as {cssPath?: string};

        expect(definition.cssPath).toBe('index.css');

        const css = readFileSync(
            path.join(fragmentDirectory, definition.cssPath ?? ''),
            'utf8'
        );

        expect(css).toContain('.nxc-contact');
        expect(css).toContain('.nxc-contact .nxc-button--primary');
    });
});
