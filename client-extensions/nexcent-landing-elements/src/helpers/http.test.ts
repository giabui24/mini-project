import {describe, expect, it} from 'vitest';

import {createRequestInit, serializeRequestBody} from './http';

describe('HTTP helpers', () => {
    it('serializes plain objects as JSON', () => {
        expect(serializeRequestBody({active: true})).toBe('{"active":true}');
    });

    it.each(['GET', 'POST', 'PUT', 'DELETE'] as const)(
        'creates a %s request',
        (method) => {
            const init = createRequestInit(method, {id: 1});

            expect(init.method).toBe(method);
            expect(init.body).toBe(
                method === 'GET' ? undefined : '{"id":1}'
            );
        }
    );
});
