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
    it('uses the stylesheet injected by the global JavaScript bundle', () => {
        const definition = JSON.parse(
            readFileSync(path.join(fragmentDirectory, 'fragment.json'), 'utf8')
        ) as {cssPath?: string};
        const html = readFileSync(
            path.join(fragmentDirectory, 'index.html'),
            'utf8'
        );
        const component = readFileSync(
            path.join(
                packageDirectory,
                'src/components/ContactForm/ContactForm.tsx'
            ),
            'utf8'
        );

        expect(definition.cssPath).toBeUndefined();
        expect(html).not.toContain('rel="stylesheet"');
        expect(component).toContain("import './contact-form.scss';");
    });
});
