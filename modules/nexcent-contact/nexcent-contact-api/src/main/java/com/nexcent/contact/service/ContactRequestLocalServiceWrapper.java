/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.nexcent.contact.service;

import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * Provides a wrapper for {@link ContactRequestLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContactRequestLocalService
 * @generated
 */
public class ContactRequestLocalServiceWrapper
	implements ContactRequestLocalService,
			   ServiceWrapper<ContactRequestLocalService> {

	public ContactRequestLocalServiceWrapper() {
		this(null);
	}

	public ContactRequestLocalServiceWrapper(
		ContactRequestLocalService contactRequestLocalService) {

		_contactRequestLocalService = contactRequestLocalService;
	}

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
	@Override
	public com.nexcent.contact.model.ContactRequest addContactRequest(
		com.nexcent.contact.model.ContactRequest contactRequest) {

		return _contactRequestLocalService.addContactRequest(contactRequest);
	}

	@Override
	public com.nexcent.contact.model.ContactRequest addContactRequest(
		long userId, long groupId, String firstName, String lastName,
		String emailAddress, String contactDetails,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return _contactRequestLocalService.addContactRequest(
			userId, groupId, firstName, lastName, emailAddress, contactDetails,
			serviceContext);
	}

	/**
	 * Creates a new contact request with the primary key. Does not add the contact request to the database.
	 *
	 * @param contactRequestId the primary key for the new contact request
	 * @return the new contact request
	 */
	@Override
	public com.nexcent.contact.model.ContactRequest createContactRequest(
		long contactRequestId) {

		return _contactRequestLocalService.createContactRequest(
			contactRequestId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactRequestLocalService.createPersistedModel(primaryKeyObj);
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
	@Override
	public com.nexcent.contact.model.ContactRequest deleteContactRequest(
		com.nexcent.contact.model.ContactRequest contactRequest) {

		return _contactRequestLocalService.deleteContactRequest(contactRequest);
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
	@Override
	public com.nexcent.contact.model.ContactRequest deleteContactRequest(
			long contactRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactRequestLocalService.deleteContactRequest(
			contactRequestId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactRequestLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _contactRequestLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _contactRequestLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _contactRequestLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _contactRequestLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _contactRequestLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _contactRequestLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _contactRequestLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _contactRequestLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.nexcent.contact.model.ContactRequest fetchContactRequest(
		long contactRequestId) {

		return _contactRequestLocalService.fetchContactRequest(
			contactRequestId);
	}

	/**
	 * Returns the contact request matching the UUID and group.
	 *
	 * @param uuid the contact request's UUID
	 * @param groupId the primary key of the group
	 * @return the matching contact request, or <code>null</code> if a matching contact request could not be found
	 */
	@Override
	public com.nexcent.contact.model.ContactRequest
		fetchContactRequestByUuidAndGroupId(String uuid, long groupId) {

		return _contactRequestLocalService.fetchContactRequestByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _contactRequestLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the contact request with the primary key.
	 *
	 * @param contactRequestId the primary key of the contact request
	 * @return the contact request
	 * @throws PortalException if a contact request with the primary key could not be found
	 */
	@Override
	public com.nexcent.contact.model.ContactRequest getContactRequest(
			long contactRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactRequestLocalService.getContactRequest(contactRequestId);
	}

	/**
	 * Returns the contact request matching the UUID and group.
	 *
	 * @param uuid the contact request's UUID
	 * @param groupId the primary key of the group
	 * @return the matching contact request
	 * @throws PortalException if a matching contact request could not be found
	 */
	@Override
	public com.nexcent.contact.model.ContactRequest
			getContactRequestByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactRequestLocalService.getContactRequestByUuidAndGroupId(
			uuid, groupId);
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
	@Override
	public java.util.List<com.nexcent.contact.model.ContactRequest>
		getContactRequests(int start, int end) {

		return _contactRequestLocalService.getContactRequests(start, end);
	}

	/**
	 * Returns all the contact requests matching the UUID and company.
	 *
	 * @param uuid the UUID of the contact requests
	 * @param companyId the primary key of the company
	 * @return the matching contact requests, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.nexcent.contact.model.ContactRequest>
		getContactRequestsByUuidAndCompanyId(String uuid, long companyId) {

		return _contactRequestLocalService.getContactRequestsByUuidAndCompanyId(
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
	@Override
	public java.util.List<com.nexcent.contact.model.ContactRequest>
		getContactRequestsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.nexcent.contact.model.ContactRequest> orderByComparator) {

		return _contactRequestLocalService.getContactRequestsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of contact requests.
	 *
	 * @return the number of contact requests
	 */
	@Override
	public int getContactRequestsCount() {
		return _contactRequestLocalService.getContactRequestsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _contactRequestLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _contactRequestLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _contactRequestLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactRequestLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public com.nexcent.contact.model.ContactRequest updateContactRequest(
		com.nexcent.contact.model.ContactRequest contactRequest) {

		return _contactRequestLocalService.updateContactRequest(contactRequest);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _contactRequestLocalService.getBasePersistence();
	}

	@Override
	public ContactRequestLocalService getWrappedService() {
		return _contactRequestLocalService;
	}

	@Override
	public void setWrappedService(
		ContactRequestLocalService contactRequestLocalService) {

		_contactRequestLocalService = contactRequestLocalService;
	}

	private ContactRequestLocalService _contactRequestLocalService;

}
// LIFERAY-SERVICE-BUILDER-HASH:-1459730197