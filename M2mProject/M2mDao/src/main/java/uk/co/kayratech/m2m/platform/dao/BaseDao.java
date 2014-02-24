package uk.co.kayratech.m2m.platform.dao;

public interface BaseDao {

	public <T> T findAudit(Class<T> classT, Object primaryKey, long revisionId);
}