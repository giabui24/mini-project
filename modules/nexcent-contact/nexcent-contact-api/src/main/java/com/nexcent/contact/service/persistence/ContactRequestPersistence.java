/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.nexcent.contact.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.nexcent.contact.exception.NoSuchContactRequestException;
import com.nexcent.contact.model.ContactRequest;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the contact request service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContactRequestUtil
 * @generated
 */
@ProviderType
public interface ContactRequestPersistence
	extends BasePersistence<ContactRequest> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ContactRequestUtil} to access the contact request persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the contact requests where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching contact requests
	 */
	public java.util.List<ContactRequest> findByUuid(String uuid);

	/**
	 * Returns a range of all the contact requests where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of contact requests
	 * @param end the upper bound of the range of contact requests (not inclusive)
	 * @return the range of matching contact requests
	 */
	public java.util.List<ContactRequest> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the contact requests where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of contact requests
	 * @param end the upper bound of the range of contact requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact requests
	 */
	public java.util.List<ContactRequest> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRequest>
			orderByComparator);

	/**
	 * Returns an ordered range of all the contact requests where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of contact requests
	 * @param end the upper bound of the range of contact requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact requests
	 */
	public java.util.List<ContactRequest> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRequest>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first contact request in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact request
	 * @throws NoSuchContactRequestException if a matching contact request could not be found
	 */
	public ContactRequest findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ContactRequest>
				orderByComparator)
		throws NoSuchContactRequestException;

	/**
	 * Returns the first contact request in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact request, or <code>null</code> if a matching contact request could not be found
	 */
	public ContactRequest fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRequest>
			orderByComparator);

	/**
	 * Removes all the contact requests where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of contact requests where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching contact requests
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the contact request where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchContactRequestException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching contact request
	 * @throws NoSuchContactRequestException if a matching contact request could not be found
	 */
	public ContactRequest findByUUID_G(String uuid, long groupId)
		throws NoSuchContactRequestException;

	/**
	 * Returns the contact request where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching contact request, or <code>null</code> if a matching contact request could not be found
	 */
	public ContactRequest fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the contact request where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching contact request, or <code>null</code> if a matching contact request could not be found
	 */
	public ContactRequest fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the contact request where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the contact request that was removed
	 */
	public ContactRequest removeByUUID_G(String uuid, long groupId)
		throws NoSuchContactRequestException;

	/**
	 * Returns the number of contact requests where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching contact requests
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the contact requests where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching contact requests
	 */
	public java.util.List<ContactRequest> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the contact requests where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of contact requests
	 * @param end the upper bound of the range of contact requests (not inclusive)
	 * @return the range of matching contact requests
	 */
	public java.util.List<ContactRequest> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the contact requests where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of contact requests
	 * @param end the upper bound of the range of contact requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact requests
	 */
	public java.util.List<ContactRequest> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRequest>
			orderByComparator);

	/**
	 * Returns an ordered range of all the contact requests where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of contact requests
	 * @param end the upper bound of the range of contact requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact requests
	 */
	public java.util.List<ContactRequest> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRequest>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first contact request in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact request
	 * @throws NoSuchContactRequestException if a matching contact request could not be found
	 */
	public ContactRequest findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactRequest>
				orderByComparator)
		throws NoSuchContactRequestException;

	/**
	 * Returns the first contact request in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact request, or <code>null</code> if a matching contact request could not be found
	 */
	public ContactRequest fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRequest>
			orderByComparator);

	/**
	 * Removes all the contact requests where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of contact requests where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching contact requests
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the contact requests where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching contact requests
	 */
	public java.util.List<ContactRequest> findByGroupId(long groupId);

	/**
	 * Returns a range of all the contact requests where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRequestModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of contact requests
	 * @param end the upper bound of the range of contact requests (not inclusive)
	 * @return the range of matching contact requests
	 */
	public java.util.List<ContactRequest> findByGroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the contact requests where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRequestModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of contact requests
	 * @param end the upper bound of the range of contact requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact requests
	 */
	public java.util.List<ContactRequest> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRequest>
			orderByComparator);

	/**
	 * Returns an ordered range of all the contact requests where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRequestModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of contact requests
	 * @param end the upper bound of the range of contact requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact requests
	 */
	public java.util.List<ContactRequest> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRequest>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first contact request in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact request
	 * @throws NoSuchContactRequestException if a matching contact request could not be found
	 */
	public ContactRequest findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactRequest>
				orderByComparator)
		throws NoSuchContactRequestException;

	/**
	 * Returns the first contact request in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact request, or <code>null</code> if a matching contact request could not be found
	 */
	public ContactRequest fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRequest>
			orderByComparator);

	/**
	 * Removes all the contact requests where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of contact requests where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching contact requests
	 */
	public int countByGroupId(long groupId);

	/**
	 * Caches the contact request in the entity cache if it is enabled.
	 *
	 * @param contactRequest the contact request
	 */
	public void cacheResult(ContactRequest contactRequest);

	/**
	 * Caches the contact requests in the entity cache if it is enabled.
	 *
	 * @param contactRequests the contact requests
	 */
	public void cacheResult(java.util.List<ContactRequest> contactRequests);

	/**
	 * Creates a new contact request with the primary key. Does not add the contact request to the database.
	 *
	 * @param contactRequestId the primary key for the new contact request
	 * @return the new contact request
	 */
	public ContactRequest create(long contactRequestId);

	/**
	 * Removes the contact request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contactRequestId the primary key of the contact request
	 * @return the contact request that was removed
	 * @throws NoSuchContactRequestException if a contact request with the primary key could not be found
	 */
	public ContactRequest remove(long contactRequestId)
		throws NoSuchContactRequestException;

	public ContactRequest updateImpl(ContactRequest contactRequest);

	/**
	 * Returns the contact request with the primary key or throws a <code>NoSuchContactRequestException</code> if it could not be found.
	 *
	 * @param contactRequestId the primary key of the contact request
	 * @return the contact request
	 * @throws NoSuchContactRequestException if a contact request with the primary key could not be found
	 */
	public ContactRequest findByPrimaryKey(long contactRequestId)
		throws NoSuchContactRequestException;

	/**
	 * Returns the contact request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contactRequestId the primary key of the contact request
	 * @return the contact request, or <code>null</code> if a contact request with the primary key could not be found
	 */
	public ContactRequest fetchByPrimaryKey(long contactRequestId);

	/**
	 * Returns all the contact requests.
	 *
	 * @return the contact requests
	 */
	public java.util.List<ContactRequest> findAll();

	/**
	 * Returns a range of all the contact requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact requests
	 * @param end the upper bound of the range of contact requests (not inclusive)
	 * @return the range of contact requests
	 */
	public java.util.List<ContactRequest> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the contact requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact requests
	 * @param end the upper bound of the range of contact requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of contact requests
	 */
	public java.util.List<ContactRequest> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRequest>
			orderByComparator);

	/**
	 * Returns an ordered range of all the contact requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact requests
	 * @param end the upper bound of the range of contact requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of contact requests
	 */
	public java.util.List<ContactRequest> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRequest>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the contact requests from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of contact requests.
	 *
	 * @return the number of contact requests
	 */
	public int countAll();

}
// LIFERAY-SERVICE-BUILDER-HASH:1857867166