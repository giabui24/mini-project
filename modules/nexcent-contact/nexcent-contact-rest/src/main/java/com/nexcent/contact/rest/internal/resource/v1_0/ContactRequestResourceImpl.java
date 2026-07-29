/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.nexcent.contact.rest.internal.resource.v1_0;

import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;

import com.nexcent.contact.rest.dto.v1_0.ContactRequest;
import com.nexcent.contact.rest.dto.v1_0.ContactRequestInput;
import com.nexcent.contact.rest.internal.captcha.ContactRequestCaptchaVerifier;
import com.nexcent.contact.rest.internal.validation.ContactRequestInputValidator;
import com.nexcent.contact.rest.internal.validation.ContactRequestValidationException;
import com.nexcent.contact.rest.internal.validation.ValidatedContactRequest;
import com.nexcent.contact.rest.resource.v1_0.ContactRequestResource;
import com.nexcent.contact.service.ContactRequestLocalService;

import jakarta.ws.rs.BadRequestException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Nexcent
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/contact-request.properties",
	scope = ServiceScope.PROTOTYPE, service = ContactRequestResource.class
)
public class ContactRequestResourceImpl extends BaseContactRequestResourceImpl {

	@Override
	public ContactRequest postContactRequest(
			ContactRequestInput contactRequestInput)
		throws Exception {

		ValidatedContactRequest request;

		try {
			request = _contactRequestInputValidator.validate(
				contactRequestInput);
		}
		catch (ContactRequestValidationException exception) {
			throw new BadRequestException(exception.getMessage(), exception);
		}

		_contactRequestCaptchaVerifier.verify(
			request, contextHttpServletRequest, contextHttpServletResponse,
			contextUriInfo, contextUser);

		ServiceContext serviceContext = _createServiceContext();

		com.nexcent.contact.model.ContactRequest contactRequest =
			_contactRequestLocalService.addContactRequest(
				serviceContext.getUserId(), serviceContext.getScopeGroupId(),
				request.getFirstName(), request.getLastName(),
				request.getEmailAddress(), request.getContactDetails(),
				serviceContext);

		return _toContactRequestDTO(contactRequest);
	}

	private ServiceContext _createServiceContext() throws Exception {
		return ServiceContextFactory.getInstance(
			com.nexcent.contact.model.ContactRequest.class.getName(),
			contextHttpServletRequest);
	}

	private ContactRequest _toContactRequestDTO(
		com.nexcent.contact.model.ContactRequest contactRequest) {

		ContactRequest contactRequestDTO = new ContactRequest();

		contactRequestDTO.setContactRequestId(
			contactRequest.getContactRequestId());
		contactRequestDTO.setCreateDate(contactRequest.getCreateDate());
		contactRequestDTO.setStatus(contactRequest.getStatus());

		return contactRequestDTO;
	}

	@Reference
	private ContactRequestCaptchaVerifier _contactRequestCaptchaVerifier;

	@Reference
	private ContactRequestInputValidator _contactRequestInputValidator;

	@Reference
	private ContactRequestLocalService _contactRequestLocalService;

}
// LIFERAY-REST-BUILDER-HASH:329087970
