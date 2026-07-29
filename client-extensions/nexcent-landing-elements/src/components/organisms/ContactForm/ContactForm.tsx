import {
    type ChangeEvent,
    type FocusEvent,
    useCallback,
    useEffect,
    useId,
    useState,
} from 'react';

import {apiClient} from '../../../api/http';

import './contact-form.scss';

export type ContactFormProps = {
    apiPath?: string;
    description?: string;
    errorMessage?: string;
    host?: HTMLElement;
    submitLabel?: string;
    submittingText?: string;
    successMessage?: string;
    title?: string;
};

type ContactField = keyof ContactFormValues;

type ContactFormValues = {
    contactDetails: string;
    emailAddress: string;
    firstName: string;
    lastName: string;
};

type ContactFormErrors = Partial<Record<ContactField, string>>;

type CaptchaChallenge = {
    image: string;
    token: string;
};

const INITIAL_VALUES: ContactFormValues = {
    contactDetails: '',
    emailAddress: '',
    firstName: '',
    lastName: '',
};

const NAME_PATTERN = /^[\p{L}\p{M}][\p{L}\p{M}' -]*$/u;
const EMAIL_PATTERN = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/;

function readStringSetting(
    host: HTMLElement | undefined,
    name: string,
    fallback: string
) {
    return host?.getAttribute(name)?.trim() || fallback;
}

export function validateContactField(
    field: ContactField,
    rawValue: string
): string | undefined {
    const value = rawValue.trim();

    if (!value) {
        return 'This field is required.';
    }

    if (field === 'firstName' || field === 'lastName') {
        if (value.length < 2) {
            return 'Enter at least 2 characters.';
        }

        if (value.length > 50) {
            return 'Enter no more than 50 characters.';
        }

        if (!NAME_PATTERN.test(value)) {
            return 'Use letters, spaces, apostrophes, or hyphens only.';
        }
    }

    if (field === 'emailAddress') {
        if (value.length > 254 || !EMAIL_PATTERN.test(value)) {
            return 'Enter a valid email address.';
        }
    }

    if (field === 'contactDetails') {
        if (value.length < 10) {
            return 'Enter at least 10 characters.';
        }

        if (value.length > 2000) {
            return 'Enter no more than 2,000 characters.';
        }
    }

    return undefined;
}

function validateContactForm(values: ContactFormValues): ContactFormErrors {
    return (Object.keys(values) as ContactField[]).reduce<ContactFormErrors>(
        (errors, field) => {
            const error = validateContactField(field, values[field]);

            if (error) {
                errors[field] = error;
            }

            return errors;
        },
        {}
    );
}

export function ContactForm({
    apiPath: apiPathProp,
    description: descriptionProp,
    errorMessage: errorMessageProp,
    host,
    submitLabel: submitLabelProp,
    submittingText: submittingTextProp,
    successMessage: successMessageProp,
    title: titleProp,
}: ContactFormProps) {
    const id = useId();
    const [errors, setErrors] = useState<ContactFormErrors>({});
    const [captchaAnswer, setCaptchaAnswer] = useState('');
    const [captchaChallenge, setCaptchaChallenge] =
        useState<CaptchaChallenge | null>(null);
    const [captchaError, setCaptchaError] = useState('');
    const [isCaptchaLoading, setIsCaptchaLoading] = useState(true);
    const [status, setStatus] = useState<
        'error' | 'idle' | 'submitting' | 'success'
    >('idle');
    const [values, setValues] = useState<ContactFormValues>(INITIAL_VALUES);

    const configuredApiPath =
        apiPathProp?.trim() ||
        readStringSetting(
            host,
            'api-path',
            '/o/nexcent-contact/v1.0/requests'
        );
    const apiPath =
        configuredApiPath === '/o/c/nxccontactrequests'
            ? '/o/nexcent-contact/v1.0/requests'
            : configuredApiPath;
    const title =
        titleProp?.trim() || readStringSetting(host, 'title', 'Contact us');
    const description =
        descriptionProp?.trim() ||
        readStringSetting(
            host,
            'description',
            'Tell us what you are working on and our team will get back to you.'
        );
    const submitLabel =
        submitLabelProp?.trim() ||
        readStringSetting(host, 'submit-label', 'Submit');
    const submittingText =
        submittingTextProp?.trim() ||
        readStringSetting(host, 'submitting-text', 'Sending…');
    const successMessage =
        successMessageProp?.trim() ||
        readStringSetting(
            host,
            'success-message',
            'Thanks! Your message has been sent.'
        );
    const errorMessage =
        errorMessageProp?.trim() ||
        readStringSetting(
            host,
            'error-message',
            'We could not send your message. Please try again.'
        );

    const loadCaptcha = useCallback(async () => {
        setIsCaptchaLoading(true);
        setCaptchaAnswer('');
        setCaptchaError('');

        try {
            const challenge = await apiClient.get<CaptchaChallenge>(
                '/o/captcha/v1.0/captcha/challenge'
            );

            if (!challenge.image || !challenge.token) {
                throw new Error('CAPTCHA challenge is incomplete.');
            }

            setCaptchaChallenge(challenge);
        }
        catch (cause) {
            console.warn('[Nexcent Contact Form CAPTCHA]', cause);
            setCaptchaChallenge(null);
            setCaptchaError(
                'Text verification could not be loaded. Please refresh it.'
            );
        }
        finally {
            setIsCaptchaLoading(false);
        }
    }, []);

    useEffect(() => {
        void loadCaptcha();
    }, [loadCaptcha]);

    const updateField = (
        event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
    ) => {
        const field = event.currentTarget.name as ContactField;
        const value = event.currentTarget.value;

        setValues((current) => ({...current, [field]: value}));

        if (errors[field]) {
            setErrors((current) => ({
                ...current,
                [field]: validateContactField(field, value),
            }));
        }
    };

    const validateOnBlur = (
        event: FocusEvent<HTMLInputElement | HTMLTextAreaElement>
    ) => {
        const field = event.currentTarget.name as ContactField;
        const value = event.currentTarget.value;

        setErrors((current) => ({
            ...current,
            [field]: validateContactField(field, value),
        }));
    };

    const handleSubmit = async () => {
        const nextErrors = validateContactForm(values);

        setErrors(nextErrors);
        setCaptchaError(
            captchaAnswer.trim() && captchaChallenge
                ? ''
                : 'Enter the text shown in the verification image.'
        );

        if (
            Object.keys(nextErrors).length > 0 ||
            !captchaAnswer.trim() ||
            !captchaChallenge
        ) {
            setStatus('idle');
            return;
        }

        let apiURL: URL;

        try {
            apiURL = new URL(apiPath, window.location.origin);

            if (apiURL.origin !== window.location.origin) {
                throw new Error('Contact API must use the current origin.');
            }
        }
        catch (cause) {
            console.warn('[Nexcent Contact Form]', cause);
            setStatus('error');
            return;
        }

        setStatus('submitting');

        try {
            await apiClient.post<void>(
                apiURL.toString(),
                {
                    contactDetails: values.contactDetails.trim(),
                    captchaAnswer: captchaAnswer.trim(),
                    captchaToken: captchaChallenge.token,
                    emailAddress: values.emailAddress.trim(),
                    firstName: values.firstName.trim(),
                    lastName: values.lastName.trim(),
                }
            );

            setValues(INITIAL_VALUES);
            setErrors({});
            setStatus('success');
            await loadCaptcha();
        }
        catch (cause) {
            console.warn('[Nexcent Contact Form]', cause);
            await loadCaptcha();
            setCaptchaError(
                'Text verification was incorrect or expired. Please try again.'
            );
            setStatus('error');
        }
    };

    const fieldProps = (field: ContactField) => {
        const errorId = `${id}-${field}-error`;

        return {
            'aria-describedby': errors[field] ? errorId : undefined,
            'aria-invalid': errors[field] ? true : undefined,
            id: `${id}-${field}`,
            name: field,
            onBlur: validateOnBlur,
            onChange: updateField,
            value: values[field],
        };
    };

    return (
        <section className="nxc-contact">
            <div className="nxc-contact__container">
                <div className="nxc-contact__intro">
                    <p className="nxc-contact__eyebrow">Nexcent</p>
                    <h2>{title}</h2>
                    <p>{description}</p>
                    </div>

                    <div
                        aria-label={title}
                        className="nxc-contact__form"
                        role="form"
                    >
                        <div className="nxc-contact__row">
                            <div className="nxc-contact__field">
                                <label htmlFor={`${id}-firstName`}>
                                    First name{' '}
                                    <span className="nxc-contact__required">*</span>
                                </label>
                                <input
                                    {...fieldProps('firstName')}
                                    autoComplete="given-name"
                                    maxLength={50}
                                    type="text"
                                />
                                {errors.firstName ? (
                                    <p
                                        className="nxc-contact__error"
                                        id={`${id}-firstName-error`}
                                    >
                                        {errors.firstName}
                                    </p>
                                ) : null}
                            </div>

                            <div className="nxc-contact__field">
                                <label htmlFor={`${id}-lastName`}>
                                    Last name{' '}
                                    <span className="nxc-contact__required">*</span>
                                </label>
                                <input
                                    {...fieldProps('lastName')}
                                    autoComplete="family-name"
                                    maxLength={50}
                                    type="text"
                                />
                                {errors.lastName ? (
                                    <p
                                        className="nxc-contact__error"
                                        id={`${id}-lastName-error`}
                                    >
                                        {errors.lastName}
                                    </p>
                                ) : null}
                            </div>
                        </div>

                        <div className="nxc-contact__field">
                            <label htmlFor={`${id}-emailAddress`}>
                                Email address{' '}
                                <span className="nxc-contact__required">*</span>
                            </label>
                            <input
                                {...fieldProps('emailAddress')}
                                autoComplete="email"
                                inputMode="email"
                                maxLength={254}
                                type="email"
                            />
                            {errors.emailAddress ? (
                                <p
                                    className="nxc-contact__error"
                                    id={`${id}-emailAddress-error`}
                                >
                                    {errors.emailAddress}
                                </p>
                            ) : null}
                        </div>

                        <div className="nxc-contact__field">
                            <label htmlFor={`${id}-contactDetails`}>
                                Contact details{' '}
                                <span className="nxc-contact__required">*</span>
                            </label>
                            <textarea
                                {...fieldProps('contactDetails')}
                                maxLength={2000}
                                rows={6}
                            />
                            {errors.contactDetails ? (
                                <p
                                    className="nxc-contact__error"
                                    id={`${id}-contactDetails-error`}
                                >
                                    {errors.contactDetails}
                                </p>
                            ) : null}
                        </div>

                        <div className="nxc-contact__captcha">
                            <div className="nxc-contact__captcha-challenge">
                                {captchaChallenge ? (
                                    <img
                                        alt="Text verification challenge"
                                        height={50}
                                        src={captchaChallenge.image}
                                        width={150}
                                    />
                                ) : (
                                    <span aria-live="polite">
                                        {isCaptchaLoading
                                            ? 'Loading text verification…'
                                            : 'Text verification unavailable.'}
                                    </span>
                                )}

                                <button
                                    aria-label="Refresh text verification"
                                    className="nxc-contact__captcha-refresh"
                                    disabled={
                                        isCaptchaLoading ||
                                        status === 'submitting'
                                    }
                                    onClick={() => void loadCaptcha()}
                                    type="button"
                                >
                                    Refresh
                                </button>
                            </div>

                            <div className="nxc-contact__field">
                                <label htmlFor={`${id}-captchaAnswer`}>
                                    Text verification{' '}
                                    <span className="nxc-contact__required">
                                        *
                                    </span>
                                </label>
                                <input
                                    aria-describedby={
                                        captchaError
                                            ? `${id}-captchaAnswer-error`
                                            : undefined
                                    }
                                    aria-invalid={
                                        captchaError ? true : undefined
                                    }
                                    autoComplete="off"
                                    disabled={
                                        isCaptchaLoading || !captchaChallenge
                                    }
                                    id={`${id}-captchaAnswer`}
                                    name="captchaAnswer"
                                    onChange={(event) => {
                                        setCaptchaAnswer(
                                            event.currentTarget.value
                                        );

                                        if (captchaError) {
                                            setCaptchaError('');
                                        }
                                    }}
                                    value={captchaAnswer}
                                />
                                {captchaError ? (
                                    <p
                                        className="nxc-contact__error"
                                        id={`${id}-captchaAnswer-error`}
                                    >
                                        {captchaError}
                                    </p>
                                ) : null}
                            </div>
                        </div>

                        <div className="nxc-contact__actions">
                            <button
                                className="nxc-button nxc-button--primary nxc-contact__submit"
                                disabled={
                                    status === 'submitting' ||
                                    isCaptchaLoading ||
                                    !captchaChallenge
                                }
                                onClick={handleSubmit}
                                type="button"
                            >
                                {status === 'submitting'
                                    ? submittingText
                                    : submitLabel}
                            </button>

                            <p
                                aria-live="polite"
                                className={`nxc-contact__status nxc-contact__status--${status}`}
                                role="status"
                            >
                                {status === 'success' ? successMessage : null}
                                {status === 'error' ? errorMessage : null}
                            </p>
                        </div>
                    </div>
            </div>
        </section>
    );
}
