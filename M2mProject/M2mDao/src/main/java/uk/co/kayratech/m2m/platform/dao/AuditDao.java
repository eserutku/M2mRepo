package uk.co.kayratech.m2m.platform.dao;

import java.io.Serializable;
import java.util.List;

import uk.co.kayratech.m2m.platform.common.exceptions.M2MBusinessException;

public interface AuditDao<T, ID extends Serializable> {

	public abstract T findAudit(Class<T> classT, ID primaryKey, long revisionId)
			throws M2MBusinessException;

	public abstract List<Number> getRevisionsForObject(Class<T> clazz, String primaryKey);

}