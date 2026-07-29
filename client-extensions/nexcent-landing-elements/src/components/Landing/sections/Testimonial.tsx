import content from '../../../../reference-assets/content.json';
import {resolveStaticAsset} from '../assets';

import {
    readBooleanSetting,
    readNumberSetting,
    readStringSetting,
} from '../runtime/fragmentSettings';

type HostProps = {
    host?: HTMLElement;
};

type ClientLogo = {
    alt: string;
    imageURL: string;
};

const FALLBACK_CLIENT_LOGOS: ClientLogo[] = content.clients.logos.map((logo) => ({
    alt: logo.alt,
    imageURL: resolveStaticAsset(logo.image),
}));

export function StaticTestimonial({host}: HostProps) {
    const quote = readStringSetting(host, 'quote', content.testimonial.quote);
    const author = readStringSetting(host, 'author', content.testimonial.author);
    const organization = readStringSetting(
        host,
        'organization',
        content.testimonial.organization
    );
    const imageURL = readStringSetting(
        host,
        'image-url',
        resolveStaticAsset(content.testimonial.image)
    );
    const imageAlt = readStringSetting(
        host,
        'image-alt',
        content.testimonial.imageAlt
    );
    const linkLabel = readStringSetting(
        host,
        'link-label',
        content.testimonial.linkLabel
    );
    const linkHref = readStringSetting(
        host,
        'link-url',
        content.testimonial.linkHref
    );
    const linkTarget = readStringSetting(host, 'link-target', '_self');
    const showPartnerLogos = readBooleanSetting(host, 'show-partner-logos', true);

    return (
        <section className="customers" id="testimonial">
            <div className="customers__container block">
                <div className="customers__item block__item">
                    <p className="customers__info block__info">{quote}</p>
                    <p className="customers__box block__box mt">{author}</p>
                    <p className="customers__text">{organization}</p>

                    <div className="customers__partner ticker">
                        <div className="customers__wrapper">
                            {showPartnerLogos ? (
                                <div className="customers__items ticker__items">
                                    {FALLBACK_CLIENT_LOGOS.map((logo, index) => (
                                        <div
                                            className="customers__icon ticker__item"
                                            key={`${logo.imageURL}-${index}`}
                                        >
                                            <img src={logo.imageURL} alt={logo.alt} />
                                        </div>
                                    ))}
                                </div>
                            ) : null}
                            <div className="customers__btn">
                                <a
                                    className="btn__wrapper"
                                    href={linkHref}
                                    target={linkTarget || undefined}
                                >
                                    {linkLabel} &nbsp; →
                                </a>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="customers__img img">
                    <img src={imageURL} alt={imageAlt} />
                </div>
            </div>
        </section>
    );
}
