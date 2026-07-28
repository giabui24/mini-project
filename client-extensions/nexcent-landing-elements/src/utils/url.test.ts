import {describe, expect, it} from 'vitest';

import {buildArticleDetailUrl, safeLinkUrl} from './url';

describe('safeLinkUrl', () => {
    it('allows portal-relative, anchor, HTTP, and HTTPS links', () => {
        expect(safeLinkUrl('/community')).toBe('/community');
        expect(safeLinkUrl('#features')).toBe('#features');
        expect(safeLinkUrl('https://example.com/path')).toBe(
            'https://example.com/path'
        );
        expect(safeLinkUrl('http://localhost:8080')).toBe(
            'http://localhost:8080'
        );
    });

    it('rejects executable and unsupported URL schemes', () => {
        expect(safeLinkUrl('javascript:alert(1)')).toBe('');
        expect(safeLinkUrl('data:text/html,unsafe')).toBe('');
        expect(safeLinkUrl('ftp://example.com/file')).toBe('');
        expect(safeLinkUrl('relative-without-a-leading-slash')).toBe('');
    });
});

describe('buildArticleDetailUrl', () => {
    it('builds full article detail URL when both siteBaseUrl and friendlyUrlPath are provided', () => {
        const result = buildArticleDetailUrl(
            '/web/nexcent-public-website',
            'digital-growth-playbook'
        );

        expect(result).toBe(
            '/web/nexcent-public-website/w/digital-growth-playbook'
        );
    });

    it('returns /w/ path when siteBaseUrl is empty', () => {
        const result = buildArticleDetailUrl(
            '',
            'digital-growth-playbook'
        );

        expect(result).toBe('/w/digital-growth-playbook');
    });

    it('normalises trailing slash on siteBaseUrl and leading/trailing slashes on slug', () => {
        const result = buildArticleDetailUrl(
            '/web/nexcent-public-website/',
            '/digital-growth-playbook/'
        );

        expect(result).toBe(
            '/web/nexcent-public-website/w/digital-growth-playbook'
        );
    });

    it('returns empty string when friendlyUrlPath is missing', () => {
        const result = buildArticleDetailUrl(
            '/web/nexcent-public-website'
        );

        expect(result).toBe('');
    });

    it('returns empty string when friendlyUrlPath is an empty string', () => {
        const result = buildArticleDetailUrl(
            '/web/nexcent-public-website',
            ''
        );

        expect(result).toBe('');
    });

    it('returns empty string when friendlyUrlPath is only slashes', () => {
        const result = buildArticleDetailUrl(
            '/web/nexcent-public-website',
            '///'
        );

        expect(result).toBe('');
    });
});
