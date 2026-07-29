/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.nexcent.contact.rest.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.util.ObjectMapperUtil;

import jakarta.annotation.Generated;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author Nexcent
 * @generated
 */
@Generated("")
@GraphQLName("ContactRequestInput")
@io.swagger.v3.oas.annotations.media.Schema(
	requiredProperties = {
		"captchaAnswer", "captchaToken", "contactDetails", "emailAddress",
		"firstName", "lastName"
	}
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "ContactRequestInput")
public class ContactRequestInput implements Serializable {

	public static ContactRequestInput toDTO(String json) {
		return ObjectMapperUtil.readValue(ContactRequestInput.class, json);
	}

	public static ContactRequestInput unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(
			ContactRequestInput.class, json);
	}

	@io.swagger.v3.oas.annotations.media.Schema
	public String getCaptchaAnswer() {
		if (_captchaAnswerSupplier != null) {
			captchaAnswer = _captchaAnswerSupplier.get();

			_captchaAnswerSupplier = null;
		}

		return captchaAnswer;
	}

	public void setCaptchaAnswer(String captchaAnswer) {
		this.captchaAnswer = captchaAnswer;

		_captchaAnswerSupplier = null;
	}

	@JsonIgnore
	public void setCaptchaAnswer(
		UnsafeSupplier<String, Exception> captchaAnswerUnsafeSupplier) {

		_captchaAnswerSupplier = () -> {
			try {
				return captchaAnswerUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotEmpty
	protected String captchaAnswer;

	@JsonIgnore
	private Supplier<String> _captchaAnswerSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	public String getCaptchaToken() {
		if (_captchaTokenSupplier != null) {
			captchaToken = _captchaTokenSupplier.get();

			_captchaTokenSupplier = null;
		}

		return captchaToken;
	}

	public void setCaptchaToken(String captchaToken) {
		this.captchaToken = captchaToken;

		_captchaTokenSupplier = null;
	}

	@JsonIgnore
	public void setCaptchaToken(
		UnsafeSupplier<String, Exception> captchaTokenUnsafeSupplier) {

		_captchaTokenSupplier = () -> {
			try {
				return captchaTokenUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotEmpty
	protected String captchaToken;

	@JsonIgnore
	private Supplier<String> _captchaTokenSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	@Size(max = 2000, min = 10)
	public String getContactDetails() {
		if (_contactDetailsSupplier != null) {
			contactDetails = _contactDetailsSupplier.get();

			_contactDetailsSupplier = null;
		}

		return contactDetails;
	}

	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;

		_contactDetailsSupplier = null;
	}

	@JsonIgnore
	public void setContactDetails(
		UnsafeSupplier<String, Exception> contactDetailsUnsafeSupplier) {

		_contactDetailsSupplier = () -> {
			try {
				return contactDetailsUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotEmpty
	protected String contactDetails;

	@JsonIgnore
	private Supplier<String> _contactDetailsSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	@Size(max = 254)
	public String getEmailAddress() {
		if (_emailAddressSupplier != null) {
			emailAddress = _emailAddressSupplier.get();

			_emailAddressSupplier = null;
		}

		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;

		_emailAddressSupplier = null;
	}

	@JsonIgnore
	public void setEmailAddress(
		UnsafeSupplier<String, Exception> emailAddressUnsafeSupplier) {

		_emailAddressSupplier = () -> {
			try {
				return emailAddressUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotEmpty
	protected String emailAddress;

	@JsonIgnore
	private Supplier<String> _emailAddressSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	@Size(max = 50, min = 2)
	public String getFirstName() {
		if (_firstNameSupplier != null) {
			firstName = _firstNameSupplier.get();

			_firstNameSupplier = null;
		}

		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;

		_firstNameSupplier = null;
	}

	@JsonIgnore
	public void setFirstName(
		UnsafeSupplier<String, Exception> firstNameUnsafeSupplier) {

		_firstNameSupplier = () -> {
			try {
				return firstNameUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotEmpty
	protected String firstName;

	@JsonIgnore
	private Supplier<String> _firstNameSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	@Size(max = 50, min = 2)
	public String getLastName() {
		if (_lastNameSupplier != null) {
			lastName = _lastNameSupplier.get();

			_lastNameSupplier = null;
		}

		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;

		_lastNameSupplier = null;
	}

	@JsonIgnore
	public void setLastName(
		UnsafeSupplier<String, Exception> lastNameUnsafeSupplier) {

		_lastNameSupplier = () -> {
			try {
				return lastNameUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotEmpty
	protected String lastName;

	@JsonIgnore
	private Supplier<String> _lastNameSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ContactRequestInput)) {
			return false;
		}

		ContactRequestInput contactRequestInput = (ContactRequestInput)object;

		return Objects.equals(toString(), contactRequestInput.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		String captchaAnswer = getCaptchaAnswer();

		if (captchaAnswer != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"captchaAnswer\": ");

			sb.append("\"");

			sb.append(_escape(captchaAnswer));

			sb.append("\"");
		}

		String captchaToken = getCaptchaToken();

		if (captchaToken != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"captchaToken\": ");

			sb.append("\"");

			sb.append(_escape(captchaToken));

			sb.append("\"");
		}

		String contactDetails = getContactDetails();

		if (contactDetails != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"contactDetails\": ");

			sb.append("\"");

			sb.append(_escape(contactDetails));

			sb.append("\"");
		}

		String emailAddress = getEmailAddress();

		if (emailAddress != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"emailAddress\": ");

			sb.append("\"");

			sb.append(_escape(emailAddress));

			sb.append("\"");
		}

		String firstName = getFirstName();

		if (firstName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"firstName\": ");

			sb.append("\"");

			sb.append(_escape(firstName));

			sb.append("\"");
		}

		String lastName = getLastName();

		if (lastName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"lastName\": ");

			sb.append("\"");

			sb.append(_escape(lastName));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@io.swagger.v3.oas.annotations.media.Schema(
		accessMode = io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY,
		defaultValue = "com.nexcent.contact.rest.dto.v1_0.ContactRequestInput",
		name = "x-class-name"
	)
	public String xClassName;

	private static String _escape(Object object) {
		return StringUtil.replace(
			String.valueOf(object), _JSON_ESCAPE_STRINGS[0],
			_JSON_ESCAPE_STRINGS[1]);
	}

	private static boolean _isArray(Object value) {
		if (value == null) {
			return false;
		}

		Class<?> clazz = value.getClass();

		return clazz.isArray();
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(_escape(entry.getKey()));
			sb.append("\": ");

			Object value = entry.getValue();

			if (_isArray(value)) {
				sb.append("[");

				Object[] valueArray = (Object[])value;

				for (int i = 0; i < valueArray.length; i++) {
					if (valueArray[i] instanceof Map) {
						sb.append(_toJSON((Map<String, ?>)valueArray[i]));
					}
					else if (valueArray[i] instanceof String) {
						sb.append("\"");
						sb.append(valueArray[i]);
						sb.append("\"");
					}
					else {
						sb.append(valueArray[i]);
					}

					if ((i + 1) < valueArray.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof Map) {
				sb.append(_toJSON((Map<String, ?>)value));
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(value));
				sb.append("\"");
			}
			else {
				sb.append(value);
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static final String[][] _JSON_ESCAPE_STRINGS = {
		{"\\", "\"", "\b", "\f", "\n", "\r", "\t"},
		{"\\\\", "\\\"", "\\b", "\\f", "\\n", "\\r", "\\t"}
	};

	private Map<String, Serializable> _extendedProperties;

}
// LIFERAY-REST-BUILDER-HASH:-109248920