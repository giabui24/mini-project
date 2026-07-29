import content from '../../../../reference-assets/content.json';
import {resolveStaticAsset} from '../../../landing/assets';

import {
    readBooleanSetting,
    readNumberSetting,
    readStringSetting,
} from '../../../landing/runtime/fragmentSettings';

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
                            className="pixelgrade__btn btn block__box"
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
