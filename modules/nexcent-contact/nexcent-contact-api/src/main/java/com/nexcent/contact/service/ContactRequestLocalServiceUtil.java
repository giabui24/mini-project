/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.nexcent.contact.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.service.Snapshot;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.nexcent.contact.model.ContactRequest;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for ContactRequest. This utility wraps
 * <code>com.nexcent.contact.service.impl.ContactRequestLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ContactRequestLocalService
 * @generated
 */
public class ContactRequestLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.nexcent.contact.service.impl.ContactRequestLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the contact request to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContactRequestLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contactRequest the contact request
	 * @return the contact request that was added
	 */
	public static ContactRequest addContactRequest(
		ContactRequest contactRequest) {

		return getService().addContactRequest(contactRequest);
	}

	public static ContactRequest addContactRequest(
		long userId, long groupId, String firstName, String lastName,
		String emailAddress, String contactDetails,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().addContactRequest(
			userId, groupId, firstName, lastName, emailAddress, contactDetails,
			serviceContext);
	}

	/**
	 * Creates a new contact request with the primary key. Does not add the contact request to the database.
	 *
	 * @param contactRequestId the primary key for the new contact request
	 * @return the new contact request
	 */
	public static ContactRequest createContactRequest(long contactRequestId) {
		return getService().createContactRequest(contactRequestId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the contact request from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContactRequestLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contactRequest the contact request
	 * @return the contact request that was removed
	 */
	public static ContactRequest deleteContactRequest(
		ContactRequest contactRequest) {

		return getService().deleteContactRequest(contactRequest);
	}

	/**
	 * Deletes the contact request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContactRequestLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contactRequestId the primary key of the contact request
	 * @return the contact request that was removed
	 * @throws PortalException if a contact request with the primary key could not be found
	 */
	public static ContactRequest deleteContactRequest(long contactRequestId)
		throws PortalException {

		return getService().deleteContactRequest(contactRequestId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.nexcent.contact.model.impl.ContactRequestModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.nexcent.contact.model.impl.ContactRequestModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static ContactRequest fetchContactRequest(long contactRequestId) {
		return getService().fetchContactRequest(contactRequestId);
	}

	/**
	 * Returns the contact request matching the UUID and group.
	 *
	 * @param uuid the contact request's UUID
	 * @param groupId the primary key of the group
	 * @return the matching contact request, or <code>null</code> if a matching contact request could not be found
	 */
	public static ContactRequest fetchContactRequestByUuidAndGroupId(
		String uuid, long groupId) {

		return getService().fetchContactRequestByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the contact request with the primary key.
	 *
	 * @param contactRequestId the primary key of the contact request
	 * @return the contact request
	 * @throws PortalException if a contact request with the primary key could not be found
	 */
	public static ContactRequest getContactRequest(long contactRequestId)
		throws PortalException {

		return getService().getContactRequest(contactRequestId);
	}

	/**
	 * Returns the contact request matching the UUID and group.
	 *
	 * @param uuid the contact request's UUID
	 * @param groupId the primary key of the group
	 * @return the matching contact request
	 * @throws PortalException if a matching contact request could not be found
	 */
	public static ContactRequest getContactRequestByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return getService().getContactRequestByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the contact requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.nexcent.contact.model.impl.ContactRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact requests
	 * @param end the upper bound of the range of contact requests (not inclusive)
	 * @return the range of contact requests
	 */
	public static List<ContactRequest> getContactRequests(int start, int end) {
		return getService().getContactRequests(start, end);
	}

	/**
	 * Returns all the contact requests matching the UUID and company.
	 *
	 * @param uuid the UUID of the contact requests
	 * @param companyId the primary key of the company
	 * @return the matching contact requests, or an empty list if no matches were found
	 */
	public static List<ContactRequest> getContactRequestsByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().getContactRequestsByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of contact requests matching the UUID and company.
	 *
	 * @param uuid the UUID of the contact requests
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of contact requests
	 * @param end the upper bound of the range of contact requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching contact requests, or an empty list if no matches were found
	 */
	public static List<ContactRequest> getContactRequestsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ContactRequest> orderByComparator) {

		return getService().getContactRequestsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of contact requests.
	 *
	 * @return the number of contact requests
	 */
	public static int getContactRequestsCount() {
		return getService().getContactRequestsCount();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the contact request in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContactRequestLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contactRequest the contact request
	 * @return the contact request that was updated
	 */
	public static ContactRequest updateContactRequest(
		ContactRequest contactRequest) {

		return getService().updateContactRequest(contactRequest);
	}

	public static ContactRequestLocalService getService() {
		return _serviceSnapshot.get();
	}

	private static final Snapshot<ContactRequestLocalService> _serviceSnapshot =
		new Snapshot<>(
			ContactRequestLocalServiceUtil.class,
			ContactRequestLocalService.class);

}
// LIFERAY-SERVICE-BUILDER-HASH:1738443677