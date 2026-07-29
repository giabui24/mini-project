/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.nexcent.contact.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ContactRequest}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContactRequest
 * @generated
 */
public class ContactRequestWrapper
	extends BaseModelWrapper<ContactRequest>
	implements ContactRequest, ModelWrapper<ContactRequest> {

	public ContactRequestWrapper(ContactRequest contactRequest) {
		super(contactRequest);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("uuid", getUuid());
		attributes.put("contactRequestId", getContactRequestId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("firstName", getFirstName());
		attributes.put("lastName", getLastName());
		attributes.put("emailAddress", getEmailAddress());
		attributes.put("contactDetails", getContactDetails());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long contactRequestId = (Long)attributes.get("contactRequestId");

		if (contactRequestId != null) {
			setContactRequestId(contactRequestId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String firstName = (String)attributes.get("firstName");

		if (firstName != null) {
			setFirstName(firstName);
		}

		String lastName = (String)attributes.get("lastName");

		if (lastName != null) {
			setLastName(lastName);
		}

		String emailAddress = (String)attributes.get("emailAddress");

		if (emailAddress != null) {
			setEmailAddress(emailAddress);
		}

		String contactDetails = (String)attributes.get("contactDetails");

		if (contactDetails != null) {
			setContactDetails(contactDetails);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public ContactRequest cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the company ID of this contact request.
	 *
	 * @return the company ID of this contact request
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the contact details of this contact request.
	 *
	 * @return the contact details of this contact request
	 */
	@Override
	public String getContactDetails() {
		return model.getContactDetails();
	}

	/**
	 * Returns the contact request ID of this contact request.
	 *
	 * @return the contact request ID of this contact request
	 */
	@Override
	public long getContactRequestId() {
		return model.getContactRequestId();
	}

	/**
	 * Returns the create date of this contact request.
	 *
	 * @return the create date of this contact request
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the email address of this contact request.
	 *
	 * @return the email address of this contact request
	 */
	@Override
	public String getEmailAddress() {
		return model.getEmailAddress();
	}

	/**
	 * Returns the first name of this contact request.
	 *
	 * @return the first name of this contact request
	 */
	@Override
	public String getFirstName() {
		return model.getFirstName();
	}

	/**
	 * Returns the group ID of this contact request.
	 *
	 * @return the group ID of this contact request
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the last name of this contact request.
	 *
	 * @return the last name of this contact request
	 */
	@Override
	public String getLastName() {
		return model.getLastName();
	}

	/**
	 * Returns the modified date of this contact request.
	 *
	 * @return the modified date of this contact request
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this contact request.
	 *
	 * @return the mvcc version of this contact request
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the primary key of this contact request.
	 *
	 * @return the primary key of this contact request
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the status of this contact request.
	 *
	 * @return the status of this contact request
	 */
	@Override
	public String getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the user ID of this contact request.
	 *
	 * @return the user ID of this contact request
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this contact request.
	 *
	 * @return the user name of this contact request
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this contact request.
	 *
	 * @return the user uuid of this contact request
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this contact request.
	 *
	 * @return the uuid of this contact request
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this contact request.
	 *
	 * @param companyId the company ID of this contact request
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the contact details of this contact request.
	 *
	 * @param contactDetails the contact details of this contact request
	 */
	@Override
	public void setContactDetails(String contactDetails) {
		model.setContactDetails(contactDetails);
	}

	/**
	 * Sets the contact request ID of this contact request.
	 *
	 * @param contactRequestId the contact request ID of this contact request
	 */
	@Override
	public void setContactRequestId(long contactRequestId) {
		model.setContactRequestId(contactRequestId);
	}

	/**
	 * Sets the create date of this contact request.
	 *
	 * @param createDate the create date of this contact request
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the email address of this contact request.
	 *
	 * @param emailAddress the email address of this contact request
	 */
	@Override
	public void setEmailAddress(String emailAddress) {
		model.setEmailAddress(emailAddress);
	}

	/**
	 * Sets the first name of this contact request.
	 *
	 * @param firstName the first name of this contact request
	 */
	@Override
	public void setFirstName(String firstName) {
		model.setFirstName(firstName);
	}

	/**
	 * Sets the group ID of this contact request.
	 *
	 * @param groupId the group ID of this contact request
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the last name of this contact request.
	 *
	 * @param lastName the last name of this contact request
	 */
	@Override
	public void setLastName(String lastName) {
		model.setLastName(lastName);
	}

	/**
	 * Sets the modified date of this contact request.
	 *
	 * @param modifiedDate the modified date of this contact request
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this contact request.
	 *
	 * @param mvccVersion the mvcc version of this contact request
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the primary key of this contact request.
	 *
	 * @param primaryKey the primary key of this contact request
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the status of this contact request.
	 *
	 * @param status the status of this contact request
	 */
	@Override
	public void setStatus(String status) {
		model.setStatus(status);
	}

	/**
	 * Sets the user ID of this contact request.
	 *
	 * @param userId the user ID of this contact request
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this contact request.
	 *
	 * @param userName the user name of this contact request
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this contact request.
	 *
	 * @param userUuid the user uuid of this contact request
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this contact request.
	 *
	 * @param uuid the uuid of this contact request
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected ContactRequestWrapper wrap(ContactRequest contactRequest) {
		return new ContactRequestWrapper(contactRequest);
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:171961502