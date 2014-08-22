package uk.co.kayratech.m2m.platform.model.factory;

import java.util.UUID;

import org.joda.time.DateTime;

import uk.co.kayratech.m2m.platform.common.exceptions.M2MSystemException;
import uk.co.kayratech.m2m.platform.model.BaseEntity;

public abstract class BaseFactory<T extends BaseEntity> {
	public abstract void populateObject(T objectToPopulate);
	
	public void populateObjectToBeSaved(T objectToPopulate) {
		populateBaseEntitySystemFields(objectToPopulate);
		populateObject(objectToPopulate);
	}
	
	public static void populateBaseEntityAndSystemFields(BaseEntity baseEntity) {
		populateBaseEntitySystemFields(baseEntity);
	}

	public static void populateBaseEntitySystemFields(BaseEntity baseEntity) {
		baseEntity.setTechnicalId(UUID.randomUUID().toString());
		baseEntity.setIntegrationId(UUID.randomUUID().toString());
		baseEntity.setCreatedDate(new DateTime());
		baseEntity.setCreatedBy("SYS_TEST");
		baseEntity.setLastModifiedDate(new DateTime());
		baseEntity.setLastModifiedBy("SYS_TEST");
		baseEntity.setModificationNo(0);
	}

	public void populateObjectAndSystemFields(T objectToPopulate) {
		populateBaseEntitySystemFields(objectToPopulate);
		populateObject(objectToPopulate);
	}

	public T getPopulatedInstanceWithSystemFields(Class<T> clazz) {
		try {
			T objectToReturn = clazz.newInstance();
			populateObjectAndSystemFields(objectToReturn);
			return objectToReturn;
		}
		catch (InstantiationException | IllegalAccessException e) {
			M2MSystemException sysEx = new M2MSystemException(
					"Exception received when instantiating class " + clazz.getSimpleName());
			sysEx.setStackTrace(e.getStackTrace());
			throw sysEx;
		}
	}
}
