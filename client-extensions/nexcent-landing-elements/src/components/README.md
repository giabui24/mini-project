# Component structure

UI components follow Atomic Design and keep their implementation, styles,
stories, and tests colocated.

```text
components/
├── atoms/
├── molecules/
└── organisms/
    └── ComponentName/
        ├── ComponentName.tsx
        ├── ComponentName.stories.tsx
        ├── ComponentName.test.tsx
        └── component-name.scss
```

- **Atoms** are indivisible UI primitives such as buttons, icons, and inputs.
- **Molecules** combine atoms into small reusable controls.
- **Organisms** are complete sections or forms with domain behaviour.

Only create a category when a real component belongs in it. The current
landing sections and Contact Form are organisms; shared API, data, and Liferay
runtime code lives outside `components/`.
