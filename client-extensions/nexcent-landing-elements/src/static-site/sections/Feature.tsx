import content from '../../../reference-assets/content.json';
import {resolveStaticAsset} from '../assets';

import {
    readBooleanSetting,
    readNumberSetting,
    readStringSetting,
} from '../runtime/fragmentSettings';

type HostProps = {
    host?: HTMLElement;
};

type FeatureKey = keyof typeof content.features;

type FeatureProps = HostProps & {
    featureKey: FeatureKey;
};

export function StaticFeature({featureKey, host}: FeatureProps) {
    const fallback = content.features[featureKey];
    const title = readStringSetting(host, 'title', fallback.title);
    const description = readStringSetting(
        host,
        'description',
        fallback.description
    );
    const buttonLabel = readStringSetting(
        host,
        'button-label',
        fallback.buttonLabel
    );
    const buttonHref = readStringSetting(
        host,
        'button-url',
        fallback.buttonHref
    );
    const buttonTarget = readStringSetting(host, 'button-target', '_self');
    const imageURL = readStringSetting(
        host,
        'image-url',
        resolveStaticAsset(fallback.image)
    );
    const imageAlt = readStringSetting(host, 'image-alt', fallback.imageAlt);
    const showButton = readBooleanSetting(host, 'show-button', true);
    const modalRule = JSON.stringify({
        id: `feature-${featureKey}`,
        slots: {
            description: {value: description},
            eyebrow: {value: 'Feature'},
            media: {
                alt: imageAlt,
                url: imageURL,
            },
            title: {value: title},
        },
        version: 1,
    });

    return (
        <section
            className="pixelgrade section"
            id={featureKey === 'primary' ? 'features' : undefined}
        >
            <div className="pixelgrade__container section__container block">
                <div className="pixelgrade__item section__item block__item">
                    <h2 className="pixelgrade__title block__title">{title}</h2>
                    <p className="block__info">{description}</p>
                    {showButton ? (
                        <a
                            aria-haspopup="dialog"
                            className="pixelgrade__btn btn block__box"
                            data-nxc-modal={modalRule}
                            href={buttonHref}
                            target={buttonTarget || undefined}
                        >
                            {buttonLabel}
                        </a>
                    ) : null}
                </div>

                <div className="pixelgrade__img section__img">
                    <img src={imageURL} alt={imageAlt} />
                </div>
            </div>
        </section>
    );
}
