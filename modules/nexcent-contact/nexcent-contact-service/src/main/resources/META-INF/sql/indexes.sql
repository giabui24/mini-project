create index IX_E5F358CF on NXC_ContactRequest (groupId);
create unique index IX_6E580DB1 on NXC_ContactRequest (uuid_[$COLUMN_LENGTH:75$], groupId);