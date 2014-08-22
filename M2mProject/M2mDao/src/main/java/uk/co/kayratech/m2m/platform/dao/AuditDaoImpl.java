package uk.co.kayratech.m2m.platform.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Repository;

import uk.co.kayratech.m2m.platform.common.exceptions.M2MBusinessException;

@Repository
public class AuditDaoImpl<T, ID extends Serializable> implements AuditDao<T, ID> {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public T findAudit(Class<T> classT, ID primaryKey, long revisionId) throws M2MBusinessException {
		return AuditReaderFactory.get(em).find(classT, primaryKey, revisionId);
	}

	@Override
	public List<Number> getRevisionsForObject(Class<T> clazz, String primaryKey) {
		AuditReader auditReader = AuditReaderFactory.get(em);
		List<Number> revisions = auditReader.getRevisions(clazz, primaryKey);
		return revisions;
	}
}
