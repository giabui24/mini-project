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

const COMMUNITY_MODAL_RULE = JSON.stringify({
    slots: {
        description: {read: 'text', selector: '.community__description'},
        eyebrow: {value: 'Community solution'},
        media: {read: 'image', selector: '.community__icon img'},
        title: {read: 'text', selector: 'h3'},
    },
    version: 1,
});

type CommunityItem = {
    description: string;
    imageAlt: string;
    imageURL: string;
    title: string;
};

const FALLBACK_COMMUNITY_ITEMS: CommunityItem[] = content.community.items.map(
    (item) => ({
        description: item.description,
        imageAlt: item.imageAlt,
        imageURL: resolveStaticAsset(item.image),
        title: item.title,
    })
);

function mapCommunityContent(
    structuredContent: HeadlessStructuredContent,
    index: number
): CommunityItem {
    const fallback =
        FALLBACK_COMMUNITY_ITEMS[index % FALLBACK_COMMUNITY_ITEMS.length] ??
        FALLBACK_COMMUNITY_ITEMS[0];
    const image = readContentImage(
        structuredContent,
        ['icon', 'image', 'iconFile'],
        {alt: fallback.imageAlt, url: fallback.imageURL}
    );

    return {
        description: readContentText(
            structuredContent,
            ['description', 'summary'],
            fallback.description
        ),
        imageAlt: readContentText(
            structuredContent,
            ['iconAlt', 'imageAlt'],
            image.alt
        ),
        imageURL: image.url,
        title: readContentText(
            structuredContent,
            ['title', 'heading'],
            structuredContent.title || fallback.title
        ),
    };
}

export function StaticCommunity({host}: HostProps) {
    const structureIdentifier = readStringSetting(
        host,
        'structure-identifier',
        'NXC Service Item'
    );
    const maxItems = readNumberSetting(host, 'max-items', 3, {
        max: 12,
        min: 1,
    });
    const title = readStringSetting(host, 'title', content.community.title);
    const description = readStringSetting(
        host,
        'description',
        content.community.description
    );
    const {error, items, status} = useStructuredContentCollection({
        fallback: FALLBACK_COMMUNITY_ITEMS,
        host,
        mapContent: mapCommunityContent,
        maxItems,
        structureIdentifier,
    });

    return (
        <section className="community" data-runtime-state={status} id="services">
            <div className="community__container">
                <div className="community__title title">
                    <h2>{title}</h2>
                    <p>{description}</p>
                </div>

                <div className="community__items mt">
                    {items.map((item, index) => (
                        <button
                            aria-haspopup="dialog"
                            className="community__item nxc-modal-trigger-card"
                            data-nxc-modal={COMMUNITY_MODAL_RULE}
                            key={`${item.title}-${index}`}
                            type="button"
                        >
                            <div className="community__icon">
                                <img src={item.imageURL} alt={item.imageAlt} />
                            </div>
                            <h3>{item.title}</h3>
                            <p className="community__description">
                                {item.description}
                            </p>
                        </button>
                    ))}
                </div>

                {error ? (
                    <span className="sr-only" role="status">
                        Services are using fallback content: {error.message}
                    </span>
                ) : null}
            </div>
        </section>
    );
}
