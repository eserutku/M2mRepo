package uk.co.kayratech.m2m.platform.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import uk.co.kayratech.aop.annotations.NoAutoLogging;
import uk.co.kayratech.m2m.platform.common.exceptions.M2MBusinessException;
import uk.co.kayratech.m2m.platform.dao.BaseDao;

@NoAutoLogging
public class BaseDaoImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements
		BaseDao<T, ID> {

	@PersistenceContext
	private EntityManager em;

	public BaseDaoImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);

		this.em = entityManager;
	}

	@Override
	public List<T> findByExample(T example, boolean eagerFetch) throws M2MBusinessException {
		// TODO Implement the method
		return null;
	}

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
