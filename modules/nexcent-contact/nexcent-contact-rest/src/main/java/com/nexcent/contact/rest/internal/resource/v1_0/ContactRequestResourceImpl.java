/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.nexcent.contact.rest.internal.resource.v1_0;

import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.LocaleUtil;

import com.nexcent.contact.rest.dto.v1_0.ContactRequest;
import com.nexcent.contact.rest.dto.v1_0.ContactRequestInput;
import com.nexcent.contact.rest.internal.captcha.ContactRequestCaptchaVerifier;
import com.nexcent.contact.rest.internal.validation.ContactRequestInputValidator;
import com.nexcent.contact.rest.internal.validation.ContactRequestValidationException;
import com.nexcent.contact.rest.internal.validation.ValidatedContactRequest;
import com.nexcent.contact.rest.resource.v1_0.ContactRequestResource;

import jakarta.ws.rs.BadRequestException;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

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
		catch (
			ContactRequestValidationException
				contactRequestValidationException) {

			throw new BadRequestException(
				contactRequestValidationException.getMessage(),
				contactRequestValidationException);
		}

		_contactRequestCaptchaVerifier.verify(
			request, contextHttpServletRequest, contextHttpServletResponse,
			contextUriInfo, contextUser);

		ServiceContext serviceContext = _createServiceContext();
		long userId = contextUser.getUserId();

		serviceContext.setAddGuestPermissions(false);
		serviceContext.setAddGroupPermissions(false);
		serviceContext.setModelPermissions(null);
		serviceContext.setUserId(userId);

		ObjectDefinition objectDefinition =
			_objectDefinitionLocalService.
				getObjectDefinitionByExternalReferenceCode(
					_OBJECT_DEFINITION_EXTERNAL_REFERENCE_CODE,
					serviceContext.getCompanyId());

		Map<String, Serializable> values = new HashMap<>();

		values.put("contactDetails", request.getContactDetails());
		values.put("emailAddress", request.getEmailAddress());
		values.put("firstName", request.getFirstName());
		values.put("lastName", request.getLastName());
		values.put("requestStatus", _REQUEST_STATUS_NEW);

		ObjectEntry objectEntry = _objectEntryLocalService.addObjectEntry(
			0, userId, objectDefinition.getObjectDefinitionId(), 0,
			LocaleUtil.toLanguageId(contextAcceptLanguage.getPreferredLocale()),
			values, serviceContext);

		if (contextUser.isDefaultUser()) {
			objectEntry.setUserName(_GUEST_AUTHOR_NAME);

			objectEntry = _objectEntryLocalService.updateObjectEntry(
				objectEntry);
		}

		return _toContactRequestDTO(objectEntry);
	}

	private ServiceContext _createServiceContext() throws Exception {
		return ServiceContextFactory.getInstance(
			ObjectEntry.class.getName(), contextHttpServletRequest);
	}

	private ContactRequest _toContactRequestDTO(ObjectEntry objectEntry)
		throws Exception {

		ContactRequest contactRequestDTO = new ContactRequest();
		Map<String, Serializable> values =
			_objectEntryLocalService.getValues(objectEntry);

		contactRequestDTO.setContactRequestId(objectEntry.getObjectEntryId());
		contactRequestDTO.setCreateDate(objectEntry.getCreateDate());
		contactRequestDTO.setStatus(
			String.valueOf(values.get("requestStatus")));

		return contactRequestDTO;
	}

	private static final String _OBJECT_DEFINITION_EXTERNAL_REFERENCE_CODE =
		"NXC_CONTACT_REQUEST";

	private static final String _GUEST_AUTHOR_NAME = "Guest";

	private static final String _REQUEST_STATUS_NEW = "new";

	@Reference
	private ContactRequestCaptchaVerifier _contactRequestCaptchaVerifier;

	@Reference
	private ContactRequestInputValidator _contactRequestInputValidator;

	@Reference
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Reference
	private ObjectEntryLocalService _objectEntryLocalService;

}

// LIFERAY-REST-BUILDER-HASH:329087970
