/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.nexcent.contact.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import com.nexcent.contact.model.ContactRequest;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ContactRequest in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContactRequestCacheModel
	implements CacheModel<ContactRequest>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ContactRequestCacheModel)) {
			return false;
		}

		ContactRequestCacheModel contactRequestCacheModel =
			(ContactRequestCacheModel)object;

		if ((contactRequestId == contactRequestCacheModel.contactRequestId) &&
			(mvccVersion == contactRequestCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, contactRequestId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", contactRequestId=");
		sb.append(contactRequestId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", firstName=");
		sb.append(firstName);
		sb.append(", lastName=");
		sb.append(lastName);
		sb.append(", emailAddress=");
		sb.append(emailAddress);
		sb.append(", contactDetails=");
		sb.append(contactDetails);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ContactRequest toEntityModel() {
		ContactRequestImpl contactRequestImpl = new ContactRequestImpl();

		contactRequestImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			contactRequestImpl.setUuid("");
		}
		else {
			contactRequestImpl.setUuid(uuid);
		}

		contactRequestImpl.setContactRequestId(contactRequestId);
		contactRequestImpl.setGroupId(groupId);
		contactRequestImpl.setCompanyId(companyId);
		contactRequestImpl.setUserId(userId);

		if (userName == null) {
			contactRequestImpl.setUserName("");
		}
		else {
			contactRequestImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			contactRequestImpl.setCreateDate(null);
		}
		else {
			contactRequestImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			contactRequestImpl.setModifiedDate(null);
		}
		else {
			contactRequestImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (firstName == null) {
			contactRequestImpl.setFirstName("");
		}
		else {
			contactRequestImpl.setFirstName(firstName);
		}

		if (lastName == null) {
			contactRequestImpl.setLastName("");
		}
		else {
			contactRequestImpl.setLastName(lastName);
		}

		if (emailAddress == null) {
			contactRequestImpl.setEmailAddress("");
		}
		else {
			contactRequestImpl.setEmailAddress(emailAddress);
		}

		if (contactDetails == null) {
			contactRequestImpl.setContactDetails("");
		}
		else {
			contactRequestImpl.setContactDetails(contactDetails);
		}

		if (status == null) {
			contactRequestImpl.setStatus("");
		}
		else {
			contactRequestImpl.setStatus(status);
		}

		contactRequestImpl.resetOriginalValues();

		return contactRequestImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		contactRequestId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		firstName = objectInput.readUTF();
		lastName = objectInput.readUTF();
		emailAddress = objectInput.readUTF();
		contactDetails = objectInput.readUTF();
		status = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(contactRequestId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (firstName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(firstName);
		}

		if (lastName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lastName);
		}

		if (emailAddress == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(emailAddress);
		}

		if (contactDetails == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(contactDetails);
		}

		if (status == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(status);
		}
	}

	public long mvccVersion;
	public String uuid;
	public long contactRequestId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String firstName;
	public String lastName;
	public String emailAddress;
	public String contactDetails;
	public String status;

}
// LIFERAY-SERVICE-BUILDER-HASH:-1819553496