/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.nexcent.contact.rest.internal.graphql.servlet.v1_0;

import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.vulcan.graphql.servlet.ServletData;

import com.nexcent.contact.rest.internal.graphql.mutation.v1_0.Mutation;
import com.nexcent.contact.rest.internal.graphql.query.v1_0.Query;
import com.nexcent.contact.rest.internal.resource.v1_0.ContactRequestResourceImpl;
import com.nexcent.contact.rest.resource.v1_0.ContactRequestResource;

import jakarta.annotation.Generated;

import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author Nexcent
 * @generated
 */
@Component(service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Mutation.setContactRequestResourceComponentServiceObjects(
			_contactRequestResourceComponentServiceObjects);
	}

	public String getApplicationName() {
		return "Liferay.Nexcent.Contact";
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/nexcent-contact-graphql/v1_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	public ObjectValuePair<Class<?>, String> getResourceMethodObjectValuePair(
		String methodName, boolean mutation) {

		if (mutation) {
			return _resourceMethodObjectValuePairs.get(
				"mutation#" + methodName);
		}

		return _resourceMethodObjectValuePairs.get("query#" + methodName);
	}

	private static final Map<String, ObjectValuePair<Class<?>, String>>
		_resourceMethodObjectValuePairs =
			new HashMap<String, ObjectValuePair<Class<?>, String>>() {
				{
					put(
						"mutation#createContactRequest",
						new ObjectValuePair<>(
							ContactRequestResourceImpl.class,
							"postContactRequest"));
					put(
						"mutation#createContactRequestBatch",
						new ObjectValuePair<>(
							ContactRequestResourceImpl.class,
							"postContactRequestBatch"));
				}
			};

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<ContactRequestResource>
		_contactRequestResourceComponentServiceObjects;

}
// LIFERAY-REST-BUILDER-HASH:1116273238