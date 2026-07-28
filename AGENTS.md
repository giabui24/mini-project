# Repository Guidelines

## Project Structure & Module Organization

This is a Liferay DXP 2026.Q2.8 workspace with two npm workspaces under `client-extensions/`.

- `nexcent-landing-elements/` contains React 18 custom elements, TypeScript source in `src/`, colocated Vitest tests, SCSS, and Liferay fragments in `fragments/`.
- `nexcent-theme/` contains Theme CSS, global assets, frontend token definitions, and the default Style Book.
- `configs/` holds environment-specific Liferay configuration.
- `reference-assets/` provides visual source material. Its upstream license is unconfirmed; do not redistribute it without checking `REFERENCE_SOURCE.md`.

Generated `build/`, `dist/`, bundles, and `node_modules/` directories must not be committed.

## Build, Test, and Development Commands

Use Node.js 20.12.2 or newer. Run frontend commands from `client-extensions/nexcent-landing-elements/`:

- `npm run dev` starts the Vite preview server.
- `npm run typecheck` runs strict TypeScript checks without emitting files.
- `npm test` runs the Vitest suite once.
- `npm run build` creates the Vite library and packages fragments.
- `npm run validate:data-sources` validates content and fragment contracts.
- `npm run package:fragments` rebuilds the fragment collection archive.

On Windows, run `gradlew.bat deploy` from the workspace root. Use `gradlew.bat deployNexcentFragments` to build and deploy only Nexcent fragments. Verify changes in this order: typecheck, tests, then build.

## Coding Style & Naming Conventions

Follow the existing TypeScript style: four-space indentation, strict typing, named React components, and ES module imports. Use PascalCase for components, camelCase for functions and variables, and kebab-case for custom elements and fragments, for example `nexcent-react-hero`. Keep component styles and tests near their implementation. No repository-wide formatter or linter is configured, so preserve surrounding formatting.

## Testing Guidelines

Name Vitest files `*.test.ts` or `*.test.tsx`. Add regression coverage for changed data mapping, runtime behavior, URL handling, or API contracts. Visual changes should also be checked in Vite and Liferay.

## Commit & Pull Request Guidelines

Use short imperative commit subjects; include a ticket prefix when available, such as `MINI-03 -> Fix contact form styles`. Pull requests should explain the change, link the issue, list verification commands, and include screenshots for UI changes. Never commit credentials or environment secrets.
