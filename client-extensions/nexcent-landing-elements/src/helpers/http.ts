export type RequestBody = BodyInit | Record<string, unknown> | undefined;

export function serializeRequestBody(body: RequestBody): BodyInit | undefined {
    if (
        body === undefined ||
        body instanceof Blob ||
        body instanceof FormData ||
        body instanceof URLSearchParams ||
        typeof body === 'string'
    ) {
        return body;
    }

    return JSON.stringify(body);
}

export function createRequestInit(
    method: 'DELETE' | 'GET' | 'POST' | 'PUT',
    body: RequestBody,
    init: RequestInit = {}
): RequestInit {
    return {
        ...init,
        body: method === 'GET' ? undefined : serializeRequestBody(body),
        method,
    };
}
