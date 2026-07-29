/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.nexcent.contact.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the ContactRequest service. Represents a row in the &quot;NXC_ContactRequest&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ContactRequestModel
 * @generated
 */
@ImplementationClassName("com.nexcent.contact.model.impl.ContactRequestImpl")
@ProviderType
public interface ContactRequest extends ContactRequestModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.nexcent.contact.model.impl.ContactRequestImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ContactRequest, Long>
		CONTACT_REQUEST_ID_ACCESSOR = new Accessor<ContactRequest, Long>() {

			@Override
			public Long get(ContactRequest contactRequest) {
				return contactRequest.getContactRequestId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ContactRequest> getTypeClass() {
				return ContactRequest.class;
			}

		};

}
// LIFERAY-SERVICE-BUILDER-HASH:444721307