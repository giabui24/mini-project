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

export function StaticStatistics({host}: HostProps) {
    const title = readStringSetting(host, 'title', content.statistics.title);
    const highlight = readStringSetting(
        host,
        'highlight',
        content.statistics.highlight
    );
    const description = readStringSetting(
        host,
        'description',
        content.statistics.description
    );
    const items = content.statistics.items.map((fallback, index) => ({
        imageAlt: readStringSetting(
            host,
            `metric-${index + 1}-icon-alt`,
            fallback.imageAlt
        ),
        imageURL: readStringSetting(
            host,
            `metric-${index + 1}-icon-url`,
            resolveStaticAsset(fallback.image)
        ),
        label: readStringSetting(
            host,
            `metric-${index + 1}-label`,
            fallback.label
        ),
        value: readStringSetting(
            host,
            `metric-${index + 1}-value`,
            fallback.value
        ),
    }));

    return (
        <section className="business" id="product">
            <div className="business__container">
                <div className="business__block block">
                    <div className="block__item">
                        <h2 className="block__title">
                            {title}{' '}
                            <span className="bright-headline">{highlight}</span>
                        </h2>
                        <p className="block__info">{description}</p>
                    </div>
                </div>

                <div className="business__items">
                    {items.map((item, index) => (
                        <div className="business__item" key={`${item.label}-${index}`}>
                            <div className="business__icon">
                                <img src={item.imageURL} alt={item.imageAlt} />
                            </div>
                            <div className="business__info">
                                <p>{item.value}</p>
                                <p>{item.label}</p>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </section>
    );
}
