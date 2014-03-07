package uk.co.kayratech.m2m.platform.model.support;

import java.util.UUID;

import org.joda.time.DateTime;

import uk.co.kayratech.m2m.platform.model.BaseEntity;

public class BaseEntitySupport {
	public static void populateBaseEntityAndSystemFields(BaseEntity baseEntity) {
		populateBaseEntityToSave(baseEntity);
		populateBaseEntitySystemFields(baseEntity);
	}

	public static void populateBaseEntityToSave(BaseEntity baseEntity) {
		baseEntity.setIntegrationId(UUID.randomUUID().toString());
	}

	public static void populateBaseEntitySystemFields(BaseEntity baseEntity) {
		baseEntity.setTechnicalId(UUID.randomUUID().toString());
		baseEntity.setCreated(new DateTime());
		baseEntity.setCreatedBy("SYS_TEST");
		baseEntity.setLastUpdate(new DateTime());
		baseEntity.setLastUpdateBy("SYS_TEST");
		baseEntity.setModificationNo(0);
	}
}
