package uk.co.kayratech.m2m.platform.dao.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.hibernate.envers.AuditReaderFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import uk.co.kayratech.aop.annotations.NoAutoLogging;
import uk.co.kayratech.m2m.platform.dao.BaseDao;

public class BaseDaoImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements
		BaseDao<T, ID> {

	private EntityManager em;

	public BaseDaoImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);

		this.em = entityManager;
	}

	@NoAutoLogging
	public T findAudit(Class<T> classT, ID primaryKey, long revisionId) {
		return AuditReaderFactory.get(em).find(classT, primaryKey, revisionId);
	}

}
