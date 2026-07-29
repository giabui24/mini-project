create table NXC_ContactRequest (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	contactRequestId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	firstName VARCHAR(75) null,
	lastName VARCHAR(75) null,
	emailAddress VARCHAR(75) null,
	contactDetails VARCHAR(75) null,
	status VARCHAR(75) null
);