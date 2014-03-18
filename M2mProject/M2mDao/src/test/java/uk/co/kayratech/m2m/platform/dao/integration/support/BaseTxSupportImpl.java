package uk.co.kayratech.m2m.platform.dao.integration.support;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import uk.co.kayratech.m2m.platform.dao.BaseDao;
import uk.co.kayratech.m2m.platform.model.BaseEntity;

@Repository
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//public abstract class BaseTxSupportImpl<T extends BaseEntity> implements BaseTxSupport<T> {
public class BaseTxSupportImpl<T extends BaseEntity> implements BaseTxSupport<T> {

	private BaseDao<T, String> dao;

	public BaseTxSupportImpl() {
		
	}

	@Override
	public T save(T objectToSave) {
		return dao.save(objectToSave);
	}

	@Override
	public List<Number> getRevisionsForObject(Class<T> clazz, String primaryKey) {
		return dao.getRevisionsForObject(clazz, primaryKey);
	}

	@Override
	public void delete(T objectToDelete) {
		dao.delete(objectToDelete);
	}
	
	@Override
	public void deleteAll() {
		dao.deleteAll();
	}
	
	@Override
	public Page<T> findAll(Pageable page) {
		return dao.findAll(page);
	}

	public BaseDao<T, String> getDao() {
		return dao;
	}

	public void setDao(BaseDao<T, String> dao) {
		this.dao = dao;
	}
}
