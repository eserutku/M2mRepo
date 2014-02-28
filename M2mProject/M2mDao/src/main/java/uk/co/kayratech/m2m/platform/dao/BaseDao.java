package uk.co.kayratech.m2m.platform.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import uk.co.kayratech.m2m.platform.common.exceptions.M2MBusinessException;

@NoRepositoryBean
public interface BaseDao<T, ID extends Serializable> extends JpaRepository<T, ID> {

	public List<T> findByExample(T example, boolean eagerFetch) throws M2MBusinessException;

	public T findAudit(Class<T> classT, ID primaryKey, long revisionId) throws M2MBusinessException;
}