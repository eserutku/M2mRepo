package uk.co.kayratech.m2m.platform.model.audit;

import java.io.Serializable;

import org.hibernate.envers.EntityTrackingRevisionListener;
import org.hibernate.envers.RevisionType;

public class CustomEntityTrackingRevisionListener implements EntityTrackingRevisionListener {
	@Override
	public void entityChanged(Class entityClass, String entityName, Serializable entityId,
			RevisionType revisionType, Object revisionEntity) {
//		String type = entityClass.getName();
//		((CustomTrackingRevisionEntity) revisionEntity).addModifiedEntityType(type);
	}

	@Override
	public void newRevision(Object revisionEntity) {
	}
}
