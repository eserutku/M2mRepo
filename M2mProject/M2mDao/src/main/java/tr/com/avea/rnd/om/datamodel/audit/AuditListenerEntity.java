package tr.com.avea.rnd.om.datamodel.audit;


public class AuditListenerEntity implements RevisionListener {

	@Override
	public void newRevision(Object revisionEntity) {
		AuditRevisionEntity revEntity = (AuditRevisionEntity)revisionEntity;
		revEntity.setUsername(InheritableThreadLocalContext.instance.get().getUsername());
	}

}
