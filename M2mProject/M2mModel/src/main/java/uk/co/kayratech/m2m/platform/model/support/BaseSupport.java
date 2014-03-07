package uk.co.kayratech.m2m.platform.model.support;

import uk.co.kayratech.m2m.platform.common.exceptions.M2MSystemException;
import uk.co.kayratech.m2m.platform.model.BaseEntity;

public abstract class BaseSupport<T extends BaseEntity> {
	public abstract void populateObjectToBeSaved(T objectToPopulate);

	public void populateObjectAndSystemFields(T objectToPopulate) {
		BaseEntitySupport.populateBaseEntitySystemFields(objectToPopulate);
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
