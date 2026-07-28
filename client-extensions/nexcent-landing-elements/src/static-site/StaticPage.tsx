import {StaticClients} from './sections/Clients';
import {StaticCommunity} from './sections/Community';
import {StaticCta} from './sections/Cta';
import {StaticFeature} from './sections/Feature';
import {StaticHero} from './sections/Hero';
import {StaticMarketing} from './sections/Marketing';
import {StaticStatistics} from './sections/Statistics';
import {StaticTestimonial} from './sections/Testimonial';
import {StaticFooter} from './shell/Footer';
import {StaticHeader} from './shell/Header';

export function StaticPage() {
    return (
        <div className="wrapper">
            <StaticHeader />
            <main className="page">
                <StaticHero />
                <StaticClients />
                <StaticCommunity />
                <StaticFeature featureKey="primary" />
                <StaticStatistics />
                <StaticFeature featureKey="secondary" />
                <StaticTestimonial />
                <StaticMarketing />
                <StaticCta />
            </main>
            <StaticFooter />
        </div>
    );
}
