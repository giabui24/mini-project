# Task 1: Legacy code cleanup

## Requirements
Remove dead standalone components, keep ContactForm and article-detail.

### Keep these:
- `src/components/ContactForm/` — still production (ContactForm.tsx, contact-form.scss, ContactForm.stories.tsx)
- `fragments/nexcent-article-detail/` — still production
- `src/utils/url.ts` — buildArticleDetailUrl used by ContentSections
- `src/utils/url.test.ts` — test for url utils
- `src/liferay/global.ts` — used by api/http.ts (transitive dep for api/structuredContent.ts which is used by static-site)

### Delete:
1. **`src/components/Hero/`** — entire dir
2. **`src/components/Services/`** — entire dir  
3. **`src/components/Features/`** — entire dir
4. **`src/components/Importer/`** — entire dir
5. **`src/components/RichText/`** — entire dir
6. **`src/App.tsx`** — Lab Status component
7. **`src/styles/`** — entire dir

### Modify:

**`src/index.tsx`**:
- Remove: imports for App, Features, Hero, ContentImporter, Services
- Remove: `import './styles/main.scss'`
- Remove: `registerReactElement` function and all its calls for dead elements
- Keep: import for ContactForm + its registration
- Keep: `registerStaticElements()` call
- Keep: `registerReactElement` function itself (needed for nexcent-contact-form)

Expected result:
```tsx
import React, {type ReactNode} from 'react';
import {createRoot, type Root} from 'react-dom/client';

import {ContactForm} from './components/ContactForm/ContactForm';
import {registerStaticElements} from './static-site/registerStaticElements';

type ElementRenderer = (element: HTMLElement) => ReactNode;

function registerReactElement(name: string, renderer: ElementRenderer) {
    if (customElements.get(name)) {
        return;
    }

    class LiferayReactElement extends HTMLElement {
        private root?: Root;

        connectedCallback() {
            if (this.root) {
                return;
            }

            this.root = createRoot(this);
            this.root.render(
                <React.StrictMode>{renderer(this)}</React.StrictMode>
            );
        }

        disconnectedCallback() {
            this.root?.unmount();
            this.root = undefined;
        }
    }

    customElements.define(name, LiferayReactElement);
}

registerReactElement('nexcent-contact-form', (element) => (
    <ContactForm host={element} />
));

registerStaticElements();
```

**`client-extension.yaml`**:
- Remove blocks for: nexcent-lab-status, nexcent-content-importer, nexcent-hero, nexcent-services, nexcent-features
- Keep: assemble, nexcent-react-runtime, nexcent-contact-form

Expected result:
```yaml
assemble:
    - from: build
      into: static

nexcent-react-runtime:
    name: Nexcent React Runtime
    scope: layout
    scriptElementAttributes:
        data-senna-track: permanent
        defer: true
        type: module
    type: globalJS
    url: index.js

nexcent-contact-form:
    cssURLs:
        - style.css
    friendlyURLMapping: nexcent-contact-form
    htmlElementName: nexcent-contact-form
    instanceable: true
    name: Nexcent Contact Form
    portletCategoryName: category.client-extensions
    type: customElement
    urls:
        - index.js
    useESM: true
```

**`package.json`**:
- Remove `exceljs` from dependencies
- Remove scripts: `generate:workbook`, `validate:data-sources`

**`src/api/structuredContent.ts`**:
- Remove exports: `flattenContentFields`, `readText`, `readNumber`, `readBoolean`, `readImage`
- Keep all other types and functions that are still used by static-site

**Delete** ContactForm.stories.tsx if it exists (it references the dead standalone element)

### Verification
After changes, run:
1. `npm run typecheck` — must pass
2. `npm test` — must pass

## Report contract
Write report to .superpowers/sdd/cleanup-plan/task-1-report.md
Include: status (DONE/BLOCKED), list of each file changed/deleted, typecheck output, test output, any concerns.