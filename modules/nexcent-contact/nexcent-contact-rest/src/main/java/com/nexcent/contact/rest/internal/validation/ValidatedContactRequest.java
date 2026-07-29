/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.nexcent.contact.rest.internal.validation;

public final class ValidatedContactRequest {

	public ValidatedContactRequest(
		String captchaAnswer, String captchaToken, String contactDetails,
		String emailAddress, String firstName, String lastName) {

		_captchaAnswer = captchaAnswer;
		_captchaToken = captchaToken;
		_contactDetails = contactDetails;
		_emailAddress = emailAddress;
		_firstName = firstName;
		_lastName = lastName;
	}

	public String getCaptchaAnswer() {
		return _captchaAnswer;
	}

	public String getCaptchaToken() {
		return _captchaToken;
	}

	public String getContactDetails() {
		return _contactDetails;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public String getFirstName() {
		return _firstName;
	}

	public String getLastName() {
		return _lastName;
	}

	private final String _captchaAnswer;
	private final String _captchaToken;
	private final String _contactDetails;
	private final String _emailAddress;
	private final String _firstName;
	private final String _lastName;

}
