/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.nexcent.contact.rest.internal.validation;

import com.nexcent.contact.rest.dto.v1_0.ContactRequestInput;

import org.junit.Assert;
import org.junit.Test;

public class ContactRequestInputValidatorTest {

	@Test
	public void testValidateNormalizesInput() {
		ContactRequestInput input = _createValidInput();

		input.setFirstName("  Ada  ");
		input.setLastName("  Lovelace  ");

		ValidatedContactRequest request = _validator.validate(input);

		Assert.assertEquals("Ada", request.getFirstName());
		Assert.assertEquals("Lovelace", request.getLastName());
	}

	@Test(expected = ContactRequestValidationException.class)
	public void testValidateRejectsInvalidEmailAddress() {
		ContactRequestInput input = _createValidInput();

		input.setEmailAddress("invalid");

		_validator.validate(input);
	}

	@Test(expected = ContactRequestValidationException.class)
	public void testValidateRejectsMissingCaptcha() {
		ContactRequestInput input = _createValidInput();

		input.setCaptchaToken(" ");

		_validator.validate(input);
	}

	@Test(expected = ContactRequestValidationException.class)
	public void testValidateRejectsNullInput() {
		_validator.validate(null);
	}

	@Test(expected = ContactRequestValidationException.class)
	public void testValidateRejectsShortContactDetails() {
		ContactRequestInput input = _createValidInput();

		input.setContactDetails("Too short");

		_validator.validate(input);
	}

	private ContactRequestInput _createValidInput() {
		ContactRequestInput input = new ContactRequestInput();

		input.setCaptchaAnswer("ABC123");
		input.setCaptchaToken("captcha-token");
		input.setContactDetails("Please contact me about the Nexcent platform.");
		input.setEmailAddress("ada@example.com");
		input.setFirstName("Ada");
		input.setLastName("Lovelace");

		return input;
	}

	private final ContactRequestInputValidator _validator =
		new ContactRequestInputValidator();

}
