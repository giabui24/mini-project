/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.nexcent.contact.rest.internal.validation;

import com.nexcent.contact.rest.dto.v1_0.ContactRequestInput;

import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Component;

@Component(service = ContactRequestInputValidator.class)
public class ContactRequestInputValidator {

	public ValidatedContactRequest validate(ContactRequestInput input) {
		if (input == null) {
			throw new ContactRequestValidationException(
				"A request body is required.");
		}

		ValidatedContactRequest request = new ValidatedContactRequest(
			_normalize(input.getCaptchaAnswer()),
			_normalize(input.getCaptchaToken()),
			_normalize(input.getContactDetails()),
			_normalize(input.getEmailAddress()),
			_normalize(input.getFirstName()), _normalize(input.getLastName()));

		_validateCaptcha(request);
		_validateNames(request);
		_validateEmailAddress(request);
		_validateContactDetails(request);

		return request;
	}

	private String _normalize(String value) {
		if (value == null) {
			return "";
		}

		return value.trim();
	}

	private void _validateCaptcha(ValidatedContactRequest request) {
		if (request.getCaptchaAnswer().isEmpty() ||
			request.getCaptchaToken().isEmpty()) {

			throw new ContactRequestValidationException(
				"Text verification is required.");
		}
	}

	private void _validateContactDetails(ValidatedContactRequest request) {
		int length = request.getContactDetails().length();

		if ((length < _MIN_CONTACT_DETAILS_LENGTH) ||
			(length > _MAX_CONTACT_DETAILS_LENGTH)) {

			throw new ContactRequestValidationException(
				"Contact details must contain 10-2000 characters.");
		}
	}

	private void _validateEmailAddress(ValidatedContactRequest request) {
		String emailAddress = request.getEmailAddress();

		if ((emailAddress.length() > _MAX_EMAIL_ADDRESS_LENGTH) ||
			!_emailAddressPattern.matcher(emailAddress).matches()) {

			throw new ContactRequestValidationException(
				"A valid email address is required.");
		}
	}

	private void _validateNames(ValidatedContactRequest request) {
		if (!_hasValidNameLength(request.getFirstName()) ||
			!_hasValidNameLength(request.getLastName())) {

			throw new ContactRequestValidationException(
				"Names must contain 2-50 characters.");
		}
	}

	private boolean _hasValidNameLength(String name) {
		int length = name.length();

		return (length >= _MIN_NAME_LENGTH) && (length <= _MAX_NAME_LENGTH);
	}

	private static final int _MAX_CONTACT_DETAILS_LENGTH = 2000;

	private static final int _MAX_EMAIL_ADDRESS_LENGTH = 254;

	private static final int _MAX_NAME_LENGTH = 50;

	private static final int _MIN_CONTACT_DETAILS_LENGTH = 10;

	private static final int _MIN_NAME_LENGTH = 2;

	private static final Pattern _emailAddressPattern = Pattern.compile(
		"^[^\\s@]+@[^\\s@]+\\.[^\\s@]{2,}$");

}
