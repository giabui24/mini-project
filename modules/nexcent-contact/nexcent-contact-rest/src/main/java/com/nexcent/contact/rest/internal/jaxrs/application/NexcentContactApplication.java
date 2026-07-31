/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.nexcent.contact.rest.internal.jaxrs.application;

import jakarta.annotation.Generated;

import jakarta.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

/**
 * @author Nexcent
 * @generated
 */
@Component(
	property = {
		"auth.verifier.guest.allowed=true", "liferay.access.control.disable=true",
		"liferay.jackson=false", "osgi.jaxrs.application.base=/nexcent-contact",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan)",
		"osgi.jaxrs.name=Liferay.Nexcent.Contact"
	},
	service = Application.class
)
@Generated("")
public class NexcentContactApplication extends Application {
}
// LIFERAY-REST-BUILDER-HASH:-760323930
