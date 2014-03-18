package uk.co.kayratech.m2m.platform.dao.integration.support;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import uk.co.kayratech.m2m.platform.model.BaseEntity;

@Repository
public interface BaseTxSupport<T extends BaseEntity> {
	T save(T objectToSave);

	void delete(T objectToSave);

	List<Number> getRevisionsForObject(Class<T> clazz, String primaryKey);
	
	void deleteAll();

	Page<T> findAll(Pageable page);
}
