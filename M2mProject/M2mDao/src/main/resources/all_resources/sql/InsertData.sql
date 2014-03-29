-- Authentication Status

INSERT INTO M2M_LOV_TYPE(CREATED, CREATED_BY, LAST_UPDATE, LAST_UPDATE_BY, MODIFICATION_NO, TECHNICAL_KEY, INTEGRATION_ID, DESCRIPTION, LOV_NAME, PARENT_LOV_TECH_KEY) VALUES (SYSDATE, 'SYSTEM', SYSDATE, 'SYSTEM', 0, '2fb67942-1655-49c5-bf8c-25a0c6f17567', '2fb67942-1655-49c5-bf8c-25a0c6f17567', 'Authentication Status of User', 'AuthenticationStatus', NULL);


INSERT INTO M2M_LOV_VALUE(CREATED, CREATED_BY, LAST_UPDATE, LAST_UPDATE_BY, MODIFICATION_NO, TECHNICAL_KEY, INTEGRATION_ID, LOV_DISC, LIC, STATUS, FROZEN, LOV_TYPE_TECH_KEY, PARENT_LIC_TECH_KEY)
VALUES(SYSDATE, 'SYSTEM', SYSDATE, 'SYSTEM', 0, '14aad0e2-b129-11e3-88fe-425861b86ab6', '14aad0e2-b129-11e3-88fe-425861b86ab6', 'AuthenticationStatus', 'AUTHENTICATED', 'IN_USE', 'F',  '2fb67942-1655-49c5-bf8c-25a0c6f17567', NULL);

INSERT INTO M2M_LOV_DISPLAY_VALUE(CREATED, CREATED_BY, LAST_UPDATE, LAST_UPDATE_BY, MODIFICATION_NO, TECHNICAL_KEY, INTEGRATION_ID, DISPLAY_VALUE, LANGUAGE, LIC_TECH_KEY) 
VALUES(SYSDATE, 'SYSTEM', SYSDATE, 'SYSTEM', 0, 'db61cc1c-974b-421d-a36b-a2e74e97bfb7', 'db61cc1c-974b-421d-a36b-a2e74e97bfb7', 'Authenticated', 'ENGLISH', '14aad0e2-b129-11e3-88fe-425861b86ab6');
INSERT INTO M2M_LOV_DISPLAY_VALUE(CREATED, CREATED_BY, LAST_UPDATE, LAST_UPDATE_BY, MODIFICATION_NO, TECHNICAL_KEY, INTEGRATION_ID, DISPLAY_VALUE, LANGUAGE, LIC_TECH_KEY) 
VALUES(SYSDATE, 'SYSTEM', SYSDATE, 'SYSTEM', 0, 'e57978e3-f363-406a-ac18-e6a7adcf3d01', 'e57978e3-f363-406a-ac18-e6a7adcf3d01', 'Doğrulandı', 'TURKISH', '14aad0e2-b129-11e3-88fe-425861b86ab6');


INSERT INTO M2M_LOV_VALUE(CREATED, CREATED_BY, LAST_UPDATE, LAST_UPDATE_BY, MODIFICATION_NO, TECHNICAL_KEY, INTEGRATION_ID, LOV_DISC, LIC, STATUS, FROZEN, LOV_TYPE_TECH_KEY, PARENT_LIC_TECH_KEY)
VALUES(SYSDATE, 'SYSTEM', SYSDATE, 'SYSTEM', 0, 'f42952c0-b129-11e3-88fe-425861b86ab6', 'f42952c0-b129-11e3-88fe-425861b86ab6', 'AuthenticationStatus', 'NOT_AUTHENTICATED', 'IN_USE', 'F',  '2fb67942-1655-49c5-bf8c-25a0c6f17567', NULL);

INSERT INTO M2M_LOV_DISPLAY_VALUE(CREATED, CREATED_BY, LAST_UPDATE, LAST_UPDATE_BY, MODIFICATION_NO, TECHNICAL_KEY, INTEGRATION_ID, DISPLAY_VALUE, LANGUAGE, LIC_TECH_KEY) 
VALUES(SYSDATE, 'SYSTEM', SYSDATE, 'SYSTEM', 0, '0df24175-ebeb-4352-9e77-80e3330da6b8', '0df24175-ebeb-4352-9e77-80e3330da6b8', 'Not Authentıcated', 'ENGLISH', 'f42952c0-b129-11e3-88fe-425861b86ab6');
INSERT INTO M2M_LOV_DISPLAY_VALUE(CREATED, CREATED_BY, LAST_UPDATE, LAST_UPDATE_BY, MODIFICATION_NO, TECHNICAL_KEY, INTEGRATION_ID, DISPLAY_VALUE, LANGUAGE, LIC_TECH_KEY) 
VALUES(SYSDATE, 'SYSTEM', SYSDATE, 'SYSTEM', 0, '961f8276-a538-435b-800b-adac96bc88d8', '961f8276-a538-435b-800b-adac96bc88d8', 'Doğrulanmadı', 'TURKISH', 'f42952c0-b129-11e3-88fe-425861b86ab6');

-- User Type

INSERT INTO M2M_LOV_TYPE(CREATED, CREATED_BY, LAST_UPDATE, LAST_UPDATE_BY, MODIFICATION_NO, TECHNICAL_KEY, INTEGRATION_ID, DESCRIPTION, LOV_NAME, PARENT_LOV_TECH_KEY) VALUES (SYSDATE, 'SYSTEM', SYSDATE, 'SYSTEM', 0, '0481ceb5-f42c-4607-ac94-c53c70cbc598', '0481ceb5-f42c-4607-ac94-c53c70cbc598', 'Type of User. Can be used in assigning permissions', 'UserType', NULL);


INSERT INTO M2M_LOV_VALUE(CREATED, CREATED_BY, LAST_UPDATE, LAST_UPDATE_BY, MODIFICATION_NO, TECHNICAL_KEY, INTEGRATION_ID, LOV_DISC, LIC, STATUS, FROZEN, LOV_TYPE_TECH_KEY, PARENT_LIC_TECH_KEY)
VALUES(SYSDATE, 'SYSTEM', SYSDATE, 'SYSTEM', 0, 'ceedcfac-0e90-448f-8421-90d3cd4b17b0', 'ceedcfac-0e90-448f-8421-90d3cd4b17b0', 'UserType', 'STANDARD', 'IN_USE', 'F',  '0481ceb5-f42c-4607-ac94-c53c70cbc598', NULL);

INSERT INTO M2M_LOV_DISPLAY_VALUE(CREATED, CREATED_BY, LAST_UPDATE, LAST_UPDATE_BY, MODIFICATION_NO, TECHNICAL_KEY, INTEGRATION_ID, DISPLAY_VALUE, LANGUAGE, LIC_TECH_KEY) 
VALUES(SYSDATE, 'SYSTEM', SYSDATE, 'SYSTEM', 0, '9d477e9d-4475-41ed-87d4-3cd35a86212b', '9d477e9d-4475-41ed-87d4-3cd35a86212b', 'Standard User', 'ENGLISH', 'ceedcfac-0e90-448f-8421-90d3cd4b17b0');
INSERT INTO M2M_LOV_DISPLAY_VALUE(CREATED, CREATED_BY, LAST_UPDATE, LAST_UPDATE_BY, MODIFICATION_NO, TECHNICAL_KEY, INTEGRATION_ID, DISPLAY_VALUE, LANGUAGE, LIC_TECH_KEY) 
VALUES(SYSDATE, 'SYSTEM', SYSDATE, 'SYSTEM', 0, 'f07df20c-d9c0-433e-8e04-dc6ac603150c', 'f07df20c-d9c0-433e-8e04-dc6ac603150c', 'Normal Kullanıcı', 'TURKISH', 'ceedcfac-0e90-448f-8421-90d3cd4b17b0');


INSERT INTO M2M_LOV_VALUE(CREATED, CREATED_BY, LAST_UPDATE, LAST_UPDATE_BY, MODIFICATION_NO, TECHNICAL_KEY, INTEGRATION_ID, LOV_DISC, LIC, STATUS, FROZEN, LOV_TYPE_TECH_KEY, PARENT_LIC_TECH_KEY)
VALUES(SYSDATE, 'SYSTEM', SYSDATE, 'SYSTEM', 0, 'cfb9dc2a-b94a-431a-bf7a-a2464deeda72', 'cfb9dc2a-b94a-431a-bf7a-a2464deeda72', 'UserType', 'ADMIN', 'IN_USE', 'F',  '0481ceb5-f42c-4607-ac94-c53c70cbc598', NULL);

INSERT INTO M2M_LOV_DISPLAY_VALUE(CREATED, CREATED_BY, LAST_UPDATE, LAST_UPDATE_BY, MODIFICATION_NO, TECHNICAL_KEY, INTEGRATION_ID, DISPLAY_VALUE, LANGUAGE, LIC_TECH_KEY) 
VALUES(SYSDATE, 'SYSTEM', SYSDATE, 'SYSTEM', 0, '4d62ae0b-944c-4688-8a11-0bbfaf3d7f43', '4d62ae0b-944c-4688-8a11-0bbfaf3d7f43', 'Administrator', 'ENGLISH', 'cfb9dc2a-b94a-431a-bf7a-a2464deeda72');
INSERT INTO M2M_LOV_DISPLAY_VALUE(CREATED, CREATED_BY, LAST_UPDATE, LAST_UPDATE_BY, MODIFICATION_NO, TECHNICAL_KEY, INTEGRATION_ID, DISPLAY_VALUE, LANGUAGE, LIC_TECH_KEY) 
VALUES(SYSDATE, 'SYSTEM', SYSDATE, 'SYSTEM', 0, 'a7422700-54d8-4b53-81a5-7cf84d51c969', 'a7422700-54d8-4b53-81a5-7cf84d51c969', 'Administratör', 'TURKISH', 'cfb9dc2a-b94a-431a-bf7a-a2464deeda72');

COMMIT;

