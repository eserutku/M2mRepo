package uk.co.kayratech.m2m.platform.model.audit;

import org.hibernate.envers.RevisionListener;

import uk.co.kayratech.m2m.platform.common.context.InheritableThreadLocalContext;


public class AuditListenerEntity implements RevisionListener {

	@Override
	public void newRevision(Object revisionEntity) {
		AuditRevisionEntity revEntity = (AuditRevisionEntity)revisionEntity;
		// TODO: Get user from Spring Security principal
		revEntity.setUsername(InheritableThreadLocalContext.instance.get().getUsername());
	}

}
