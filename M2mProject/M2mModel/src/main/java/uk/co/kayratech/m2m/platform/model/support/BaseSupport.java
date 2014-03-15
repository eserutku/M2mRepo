package uk.co.kayratech.m2m.platform.model.support;

import java.util.UUID;

import org.joda.time.DateTime;

import uk.co.kayratech.m2m.platform.common.exceptions.M2MSystemException;
import uk.co.kayratech.m2m.platform.model.BaseEntity;

public abstract class BaseSupport<T extends BaseEntity> {
	public abstract void populateObjectToBeSaved(T objectToPopulate);
	
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

	public void populateObjectAndSystemFields(T objectToPopulate) {
		populateBaseEntitySystemFields(objectToPopulate);
		populateObjectToBeSaved(objectToPopulate);
	}

	public T getPopulatedInstanceToBeSaved(Class<T> clazz) {
		try {
			T objectToReturn = clazz.newInstance();
			populateObjectToBeSaved(objectToReturn);
			return objectToReturn;
		}
		catch (InstantiationException | IllegalAccessException e) {
			M2MSystemException sysEx = new M2MSystemException(
					"Exception received when instantiating class " + clazz.getSimpleName());
			sysEx.setStackTrace(e.getStackTrace());
			throw sysEx;
		}
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
