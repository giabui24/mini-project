import content from '../../../reference-assets/content.json';
import {
    readBooleanSetting,
    readStringSetting,
} from '../../landing/runtime/fragmentSettings';

type HostProps = {
    host?: HTMLElement;
};

export function StaticCta({host}: HostProps) {
    const title = readStringSetting(host, 'title', content.cta.title);
    const buttonLabel = readStringSetting(
        host,
        'button-label',
        content.cta.buttonLabel
    );
    const buttonHref = readStringSetting(
        host,
        'button-url',
        content.cta.buttonHref
    );
    const buttonTarget = readStringSetting(host, 'button-target', '_self');
    const showButton = readBooleanSetting(host, 'show-button', true);

    return (
        <section className="suscipit" id="faq">
            <div className="suscipit__container block">
                <div className="suscipit__info block__item">
                    <h2 className="block__title big-fs">{title}</h2>
                    {showButton ? (
                        <a
                            className="suscipit__btn btn block__box"
                            href={buttonHref}
                            target={buttonTarget || undefined}
                        >
                            {buttonLabel} &nbsp; →
                        </a>
                    ) : null}
                </div>
            </div>
        </section>
    );
}
