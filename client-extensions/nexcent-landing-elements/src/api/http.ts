import {getLiferay} from '../liferay/global';
import {
    createRequestInit,
    type RequestBody,
} from '../helpers/http';

export class ApiError extends Error {
    constructor(
        message: string,
        readonly status: number,
        readonly responseBody?: string
    ) {
        super(message);
        this.name = 'ApiError';
    }
}

export async function portalFetch<T>(
    path: string,
    init: RequestInit = {}
): Promise<T> {
    const url = new URL(path, window.location.origin);
    const headers = new Headers(init.headers);
    const authToken = getLiferay()?.authToken;
    const isFormData = init.body instanceof FormData;

    headers.set('Accept', 'application/json');

    if (init.body && !isFormData && !headers.has('Content-Type')) {
        headers.set('Content-Type', 'application/json');
    }

    if (authToken) {
        headers.set('x-csrf-token', authToken);
    }

    const response = await fetch(url, {
        credentials: 'same-origin',
        ...init,
        headers,
    });

    if (!response.ok) {
        const responseBody = await response.text();

        throw new ApiError(
            `Liferay API request failed with HTTP ${response.status}.`,
            response.status,
            responseBody
        );
    }

    if (response.status === 204) {
        return undefined as T;
    }

    if (typeof response.text !== 'function') {
        return (await response.json()) as T;
    }

    const responseBody = await response.text();

    if (!responseBody) {
        return undefined as T;
    }

    if (response.headers.get('content-type')?.includes('application/json')) {
        return JSON.parse(responseBody) as T;
    }

    return responseBody as T;
}

export function portalGet<T>(
    path: string,
    init?: RequestInit
): Promise<T> {
    return portalFetch<T>(path, createRequestInit('GET', undefined, init));
}

export function portalPost<T>(
    path: string,
    body?: RequestBody,
    init?: RequestInit
): Promise<T> {
    return portalFetch<T>(path, createRequestInit('POST', body, init));
}

export function portalPut<T>(
    path: string,
    body?: RequestBody,
    init?: RequestInit
): Promise<T> {
    return portalFetch<T>(path, createRequestInit('PUT', body, init));
}

export function portalDelete<T>(
    path: string,
    body?: RequestBody,
    init?: RequestInit
): Promise<T> {
    return portalFetch<T>(path, createRequestInit('DELETE', body, init));
}

export const apiClient = {
    delete: portalDelete,
    get: portalGet,
    post: portalPost,
    put: portalPut,
};
