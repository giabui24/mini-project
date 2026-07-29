import content from '../../../reference-assets/content.json';
import {resolveStaticAsset} from '../assets';
import {
    type HeadlessStructuredContent,
    readContentImage,
    readContentText,
} from '../headless/headlessContentClient';
import {useStructuredContentCollection} from '../headless/useStructuredContentCollection';
import {
    readBooleanSetting,
    readNumberSetting,
    readStringSetting,
} from '../runtime/fragmentSettings';
import {buildArticleDetailUrl} from '../../utils/url';

type HostProps = {
    host?: HTMLElement;
};

type MarketingItem = {
    imageAlt: string;
    imageURL: string;
    linkHref: string;
    linkLabel: string;
    linkTarget: string;
    title: string;
};

const FALLBACK_MARKETING_ITEMS: MarketingItem[] = content.marketing.items.map(
    (item) => ({
        imageAlt: item.imageAlt,
        imageURL: resolveStaticAsset(item.image),
        linkHref: item.linkHref,
        linkLabel: item.linkLabel,
        linkTarget: '_self',
        title: item.title,
    })
);

function mapMarketingContent(
    structuredContent: HeadlessStructuredContent,
    index: number,
    siteBaseUrl: string
): MarketingItem {
    const fallback =
        FALLBACK_MARKETING_ITEMS[index % FALLBACK_MARKETING_ITEMS.length] ??
        FALLBACK_MARKETING_ITEMS[0];

    // Priority 1: coverImage (top-level Structured Content field)
    // Priority 2: fallback to contentFields lookup
    const coverImage = structuredContent.coverImage;
    const coverImageUrl = coverImage?.image?.contentUrl?.trim() || '';
    const coverImageDescription =
        coverImage?.description?.trim() ||
        coverImage?.image?.description?.trim() ||
        '';
    const fallbackImage = coverImageUrl
        ? {
              alt:
                  coverImageDescription ||
                  structuredContent.title?.trim() ||
                  fallback.imageAlt,
              url: coverImageUrl,
          }
        : readContentImage(
              structuredContent,
              ['coverImage', 'thumbnail', 'image', 'thumbnailFile'],
              {alt: fallback.imageAlt, url: fallback.imageURL}
          );

    const articleUrl = buildArticleDetailUrl(
        siteBaseUrl,
        structuredContent.friendlyUrlPath
    );

    return {
        imageAlt: fallbackImage.alt,
        imageURL: fallbackImage.url,
        linkHref: articleUrl || fallback.linkHref,
        linkLabel: readContentText(
            structuredContent,
            ['linkLabel', 'ctaLabel'],
            ''
        ),
        linkTarget: '_self',
        title: readContentText(
            structuredContent,
            ['title', 'heading'],
            structuredContent.title || fallback.title
        ),
    };
}

export function StaticMarketing({host}: HostProps) {
    const structureIdentifier = readStringSetting(
        host,
        'structure-identifier',
        'NXC Community Card'
    );
    const maxItems = readNumberSetting(host, 'max-items', 3, {
        max: 12,
        min: 1,
    });
    const title = readStringSetting(host, 'title', content.marketing.title);
    const description = readStringSetting(
        host,
        'description',
        content.marketing.description
    );
    const readMoreLabel = readStringSetting(host, 'read-more-label', 'Readmore');
    const siteBaseUrl = readStringSetting(host, 'data-site-base-url', '');
    const {error, items, status} = useStructuredContentCollection({
        fallback: FALLBACK_MARKETING_ITEMS,
        host,
        mapContent: (content, index) =>
            mapMarketingContent(content, index, siteBaseUrl),
        maxItems,
        structureIdentifier,
    });

    return (
        <section className="marketing" data-runtime-state={status}>
            <div className="marketing__container">
                <div className="marketing__title title">
                    <h2>{title}</h2>
                    <p>{description}</p>
                </div>

                <div className="marketing__items mt">
                    {items.map((item, index) => (
                        <article className="marketing__item" key={`${item.title}-${index}`}>
                            <div className="marketing__img img">
                                <img src={item.imageURL} alt={item.imageAlt} />
                            </div>
                            <div className="marketing__info">
                                <p>{item.title}</p>
                                <a
                                    className="btn__wrapper"
                                    href={item.linkHref}
                                    target={item.linkTarget || undefined}
                                >
                                    {item.linkLabel || readMoreLabel} &nbsp; →
                                </a>
                            </div>
                        </article>
                    ))}
                </div>

                {error ? (
                    <span className="sr-only" role="status">
                        Marketing cards are using fallback content: {error.message}
                    </span>
                ) : null}
            </div>
        </section>
    );
}
