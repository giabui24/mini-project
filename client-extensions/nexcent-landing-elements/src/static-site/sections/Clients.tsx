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

type ClientLogo = {
    alt: string;
    imageURL: string;
};

const FALLBACK_CLIENT_LOGOS: ClientLogo[] = content.clients.logos.map((logo) => ({
    alt: logo.alt,
    imageURL: resolveStaticAsset(logo.image),
}));

function ClientLogos({
    duplicate = false,
    logos,
}: {
    duplicate?: boolean;
    logos: ClientLogo[];
}) {
    return (
        <div
            aria-hidden={duplicate || undefined}
            className="ticker__items ticker__marquee"
        >
            {logos.map((logo, index) => (
                <div className="ticker__item" key={`${logo.imageURL}-${index}-${duplicate}`}>
                    <img src={logo.imageURL} alt={duplicate ? '' : logo.alt} />
                </div>
            ))}
        </div>
    );
}

function readClientLogos(host: HTMLElement | undefined): ClientLogo[] {
    return FALLBACK_CLIENT_LOGOS.map((fallback, index) => ({
        alt: readStringSetting(host, `logo-${index + 1}-alt`, fallback.alt),
        imageURL: readStringSetting(
            host,
            `logo-${index + 1}-url`,
            fallback.imageURL
        ),
    }));
}

export function StaticClients({host}: HostProps) {
    const logos = readClientLogos(host);
    const title = readStringSetting(host, 'title', content.clients.title);
    const description = readStringSetting(
        host,
        'description',
        content.clients.description
    );
    const showTicker = readBooleanSetting(host, 'show-ticker', true);

    return (
        <section className="clients">
            <div className="clients__container">
                <div className="title">
                    <h2>{title}</h2>
                    <p>{description}</p>
                </div>

                <div className="clients__wrapper mt">
                    <div className="clients__ticker ticker">
                        <div className="clients__box ticker__wrapper">
                            <ClientLogos logos={logos} />
                            {showTicker ? <ClientLogos duplicate logos={logos} /> : null}
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
}
