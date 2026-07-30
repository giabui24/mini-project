export type FragmentConfiguration = Record<string, string | undefined>;

function toCamelCase(value: string) {
    return value.replace(/-([a-z])/g, (_, character: string) =>
        character.toUpperCase()
    );
}

export function getFragmentConfiguration(
    host: HTMLElement
): FragmentConfiguration {
    return Array.from(host.attributes).reduce<FragmentConfiguration>(
        (configuration, attribute) => {
            configuration[toCamelCase(attribute.name)] =
                attribute.value.trim() || undefined;

            return configuration;
        },
        {}
    );
}
