# Nexcent Global Modal

Place the **Nexcent Global Modal** fragment on a page once. Any element on the
same page can open it by providing a versioned JSON rule in `data-nxc-modal`.

```html
<button
    aria-haspopup="dialog"
    data-nxc-modal='{
        "version": 1,
        "slots": {
            "title": {"read": "text", "selector": ".card__title"},
            "description": {"read": "text", "selector": ".card__summary"},
            "media": {"read": "image", "selector": ".card__image"}
        }
    }'
>
    ...
</button>
```

The required `title` slot and optional `eyebrow`, `description`,
`primaryValue`, and fact label/value slots accept either a direct value or a
text selector:

```json
{"value": "Latest metric"}
```

```json
{"read": "text", "selector": ".metric__value"}
```

The optional `media` slot accepts a direct image or an image selector:

```json
{"alt": "Members", "url": "/documents/members.svg"}
```

```json
{"read": "image", "selector": ".metric__icon"}
```

Selectors are evaluated only inside the clicked trigger. The resolved
document is published through `nexcent:modal:open`; closing the modal publishes
`nexcent:modal:closed`.
