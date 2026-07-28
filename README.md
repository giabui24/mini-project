# Project Structure

## Overview

This repository is a **Liferay DXP Workspace** for the Nexcent landing site. It combines:

- Liferay workspace and environment configuration;
- React-based Liferay client extensions;
- a shared Liferay theme and Style Book;
- static reference assets used by the landing-elements client extension.

The workspace targets **Liferay DXP 2026.Q2.8** and uses the Liferay Gradle Workspace plugin **16.0.5**.

## Directory Map

```text
mini-project/
├── client-extensions/
│   ├── nexcent-landing-elements/   React custom elements and fragments
│   │   └── reference-assets/       Static assets and visual reference source
│   └── nexcent-theme/              Theme CSS, global assets, favicon, Style Book
├── configs/
│   ├── common/                     Settings shared by all environments
│   ├── dev/                        Development configuration
│   ├── docker/                     Docker configuration
│   ├── local/                      Local workstation configuration
│   ├── prod/                       Production configuration
│   └── uat/                        User-acceptance environment configuration
├── gradle/wrapper/                 Gradle wrapper runtime
├── modules/                        Liferay modules and development license
├── themes/                         Reserved for conventional Liferay theme projects
├── build.gradle                    Root Gradle build customization
├── gradle.properties               Liferay product and workspace settings
├── settings.gradle                 Liferay Workspace plugin configuration
├── package.json                    npm/yarn workspace declaration
├── platform.bndrun                 OSGi target-platform resolution
├── Dockerfile.ext                  Liferay Docker image customization
└── GETTING_STARTED.md              General Liferay Workspace commands
```

Generated or local-only directories such as `build/`, `bundles/`, `.gradle/`, `node_modules/`, and `node_modules_cache/` are not source-code areas.

## Main Components

### `client-extensions/nexcent-landing-elements`

A TypeScript, React 18, Vite, and SCSS package that builds the Liferay landing-page client extensions. This is the main implementation package and contains its visual assets and reference material under `reference-assets/`.

Important areas:

- `src/components/` contains standalone React custom elements such as the Contact Form.
- `src/api/` contains HTTP and Liferay Structured Content access.
- `src/liferay/` contains Liferay runtime integration.
- `src/static-site/` contains the landing sections, page shell, headless-content client, and custom-element registration.
- `fragments/` contains Liferay Fragment definitions for page sections such as Header, Hero, Clients, Statistics, CTA, and Footer.
- `scripts/` contains the Fragment Set packaging utility.
- `client-extension.yaml` declares the shared React runtime and Contact Form custom element.
- `reference-assets/` supplies the images, icons, and fallback content used by the landing components.

Useful commands:

```bash
cd client-extensions/nexcent-landing-elements
npm install
npm run dev
npm run build
npm test
npm run typecheck
```

Node.js **20.12.2** is required.

### `client-extensions/nexcent-theme`

The shared visual foundation for the Nexcent site. It packages:

- Theme CSS and editable frontend tokens;
- layout-scoped global CSS;
- layout-scoped global JavaScript;
- the project favicon;
- the `Nexcent Default` Style Book values.

See `client-extensions/nexcent-theme/README.md` for deployment, Style Book import, and Liferay Page Builder class mappings.

#### Asset source: `client-extensions/nexcent-landing-elements/reference-assets`

This directory belongs conceptually to `nexcent-landing-elements` and is retained only as its asset and visual-reference source. Production functionality is implemented and packaged from `client-extensions/nexcent-landing-elements`.

The imported reference assets have no confirmed upstream license; consult `client-extensions/nexcent-landing-elements/reference-assets/REFERENCE_SOURCE.md` before redistribution or commercial reuse.

### `configs`

Environment-specific Liferay portal properties live under `local`, `dev`, `docker`, `uat`, and `prod`. Shared setup properties live under `common`. Some environments also include OSGi configuration, such as Elasticsearch settings.

## Build and Deployment

Common workspace commands:

```bash
./gradlew deploy
./gradlew resolve
./gradlew initBundle
./gradlew distBundleZip
./gradlew buildDockerImage
```

On Windows, use `gradlew.bat` instead of `./gradlew`.

The root `package.json` also defines both client-extension directories as JavaScript workspaces.

## Suggested Starting Points

- Read `GETTING_STARTED.md` for general Liferay workspace operations.
- Start frontend feature work in `client-extensions/nexcent-landing-elements/src/`.
- Use `client-extensions/nexcent-landing-elements/reference-assets/` as the package's asset/reference source.
- Start shared styling work in `client-extensions/nexcent-theme/`.
- Update the appropriate directory under `configs/` for environment-specific portal behavior.
