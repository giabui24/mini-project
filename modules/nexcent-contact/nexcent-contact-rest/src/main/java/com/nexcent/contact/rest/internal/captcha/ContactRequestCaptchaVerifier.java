/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.nexcent.contact.rest.internal.captcha;

import com.liferay.captcha.rest.dto.v1_0.Captcha;
import com.liferay.captcha.rest.resource.v1_0.CaptchaResource;
import com.liferay.portal.kernel.model.User;

import com.nexcent.contact.rest.internal.validation.ValidatedContactRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.core.UriInfo;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = ContactRequestCaptchaVerifier.class)
public class ContactRequestCaptchaVerifier {

	public void verify(
		ValidatedContactRequest request,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, UriInfo uriInfo, User user) {

		Captcha captcha = new Captcha();

		captcha.setAnswer(request.getCaptchaAnswer());
		captcha.setToken(request.getCaptchaToken());

		try {
			CaptchaResource captchaResource = _captchaResourceFactory.create(
			).checkPermissions(
				false
			).httpServletRequest(
				httpServletRequest
			).httpServletResponse(
				httpServletResponse
			).uriInfo(
				uriInfo
			).user(
				user
			).build();

			captchaResource.postCaptchaResponse(captcha);
		}
		catch (ForbiddenException forbiddenException) {
			throw forbiddenException;
		}
		catch (Exception exception) {
			throw new BadRequestException(
				"Text verification is incorrect or expired.", exception);
		}
	}

	@Reference
	private CaptchaResource.Factory _captchaResourceFactory;

}
