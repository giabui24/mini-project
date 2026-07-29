# Nexcent landing React runtime

This package converts the markup and behaviour from `reference-assets/` into
React components while keeping those resources as the visual baseline.

## Storybook component preview

Each React component can run without Liferay and without the Site Shell or
Headless Delivery APIs in Storybook. Preview data is read from:

```text
client-extensions/nexcent-landing-elements/reference-assets/content.json
```

Run:

```bash
cd client-extensions/nexcent-landing-elements
npm ci
npm run storybook
```

Open:

```text
http://localhost:6006
```

Stories render Header, each body section, Footer, and Contact Form independently.
No request is sent to Liferay when a component is rendered without a custom
element host.

## Runtime contract

- One Vite bundle registers all `nexcent-react-*` custom elements.
- Each element renders into a Shadow DOM to prevent the original static reset
  and component selectors from leaking into Liferay.
- The original `rem` scale is converted to pixels inside the Shadow DOM because
  the static source was authored against a 62.5% root font size.
- Static preview copy is read from `reference-assets/content.json`; it
  is not embedded in JSX.
- The Hero carousel is implemented with React state and timers, so Swiper and
  AOS CDNs are no longer runtime dependencies.

Attach **Nexcent React Runtime** as Global JavaScript to the Master Page before
using the Fragment shells.

## Fragment source and packaging

The production Fragment Set source is colocated with the React runtime:

```text
client-extensions/nexcent-landing-elements/fragments/
├── collection.json
├── nexcent-react-header/
├── nexcent-react-hero/
├── ...
└── nexcent-react-footer/
```

This directory is the single source of truth for React Fragment shells. Files
under `training/master-track-code-labs/fragments` are training examples and must
not be used to package the production React Fragment Set.

Build the importable Fragment Set ZIP with:

```bash
cd client-extensions/nexcent-landing-elements
npm run package:fragments
```

Output:

```text
build/fragments/collections-nexcent-components.zip
```

The legacy PowerShell helper under `training/master-track-code-labs/scripts`
delegates to this command for backwards compatibility.

## Body content strategy

Production body sections intentionally use two data paths:

```text
Fragment Settings → custom-element attributes → React props
├── Clients
├── Feature Primary
├── Statistics
├── Feature Secondary
├── Testimonial
└── CTA

Headless Delivery API
├── Hero
├── Community / Services
└── Marketing / Articles
```

The three Headless sections resolve a configured Content Structure identifier,
load approved Structured Content, filter inactive entries, sort by `sortOrder`,
and share one browser request cache.

```text
GET /o/headless-delivery/v1.0/sites/{siteId}/content-structures?pageSize=200
GET /o/headless-delivery/v1.0/content-structures/{structureId}/structured-contents?pageSize=100
```

`content.json` remains the preview fixture and runtime fallback; it is not
the intended production source for body copy.

Validate the split locally with:

```bash
npm run typecheck
npm test
npm run build
```

## Production Site Shell

`nexcent-react-header` and `nexcent-react-footer` are production components.
Their fragments serialize permission-filtered Navigation Menu items, account
state, and site identity into nonce-protected JSON script elements. The React
components read those embedded props without an additional REST request.

If the embedded props are missing or invalid, both components render the
bundled static fallback and mark their custom-element host with a fallback
content state.

Storybook is the visual preview for the individual React components and uses
the same fallback data as the production custom elements.
