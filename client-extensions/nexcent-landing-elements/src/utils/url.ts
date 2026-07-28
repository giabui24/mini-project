const SAFE_PROTOCOLS = new Set(['http:', 'https:']);

export function safeLinkUrl(value: string): string {
    const url = value.trim();

    if (!url) {
        return '';
    }

    if (url.startsWith('/') || url.startsWith('#')) {
        return url;
    }

    try {
        const parsed = new URL(url);

        return SAFE_PROTOCOLS.has(parsed.protocol) ? url : '';
    }
    catch {
        return '';
    }
}

/**
 * Builds an Article detail URL using the current Site base URL and
 * the Structured Content's friendly URL path.
 *
 * Normalises both inputs by stripping leading/trailing slashes.
 *
 * @example
 *   buildArticleDetailUrl('/web/nexcent-public-website', 'digital-growth-playbook')
 *   // => '/web/nexcent-public-website/w/digital-growth-playbook'
 *
 * @example
 *   buildArticleDetailUrl('', 'digital-growth-playbook')
 *   // => '/w/digital-growth-playbook'
 *
 * @example
 *   buildArticleDetailUrl('/web/nexcent-public-website', '')
 *   // => ''
 */
export function buildArticleDetailUrl(
    siteBaseUrl: string,
    friendlyUrlPath?: string
): string {
    const base = siteBaseUrl.trim().replace(/\/+$/g, '');
    const slug = (friendlyUrlPath ?? '').trim().replace(/^\/+|\/+$/g, '');

    if (!slug) {
        return '';
    }

    return base ? `${base}/w/${slug}` : `/w/${slug}`;
}
