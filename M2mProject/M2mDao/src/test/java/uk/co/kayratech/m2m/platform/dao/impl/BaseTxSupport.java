package uk.co.kayratech.m2m.platform.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import uk.co.kayratech.m2m.platform.model.BaseEntity;

@Repository
public interface BaseTxSupport<T extends BaseEntity> {
	T save(T objectToSave);

	void delete(T objectToSave);

	List<Number> getRevisionsForObject(Class<T> clazz, String primaryKey);
	
	public void deleteAllTableData();
}
