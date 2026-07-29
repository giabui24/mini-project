/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.nexcent.contact.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import com.nexcent.contact.model.ContactRequest;
import com.nexcent.contact.service.base.ContactRequestLocalServiceBaseImpl;

import java.util.Date;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.nexcent.contact.model.ContactRequest",
	service = AopService.class
)
public class ContactRequestLocalServiceImpl
	extends ContactRequestLocalServiceBaseImpl {

	public ContactRequest addContactRequest(
		long userId, long groupId, String firstName, String lastName,
		String emailAddress, String contactDetails,
		ServiceContext serviceContext) {

		ContactRequest contactRequest = contactRequestPersistence.create(
			counterLocalService.increment());

		contactRequest.setUuid(PortalUUIDUtil.generate());
		contactRequest.setGroupId(groupId);
		contactRequest.setCompanyId(serviceContext.getCompanyId());

		_setAuditFields(contactRequest, userId);
		_setContactFields(
			contactRequest, firstName, lastName, emailAddress, contactDetails);

		return contactRequestPersistence.update(contactRequest);
	}

	private void _setAuditFields(ContactRequest contactRequest, long userId) {
		Date now = new Date();
		User user = userLocalService.fetchUser(userId);

		contactRequest.setUserId(userId);
		contactRequest.setUserName(
			(user == null) ? _GUEST_USER_NAME : user.getFullName());
		contactRequest.setCreateDate(now);
		contactRequest.setModifiedDate(now);
	}

	private void _setContactFields(
		ContactRequest contactRequest, String firstName, String lastName,
		String emailAddress, String contactDetails) {

		contactRequest.setFirstName(firstName.trim());
		contactRequest.setLastName(lastName.trim());
		contactRequest.setEmailAddress(emailAddress.trim());
		contactRequest.setContactDetails(contactDetails.trim());
		contactRequest.setStatus(_STATUS_NEW);
	}

	private static final String _GUEST_USER_NAME = "Guest";

	private static final String _STATUS_NEW = "new";

}
// LIFERAY-SERVICE-BUILDER-HASH:2007313057
