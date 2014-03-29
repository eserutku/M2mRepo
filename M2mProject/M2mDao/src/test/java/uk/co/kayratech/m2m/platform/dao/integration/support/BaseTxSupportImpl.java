package uk.co.kayratech.m2m.platform.dao.integration.support;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import uk.co.kayratech.m2m.platform.common.exceptions.M2MSystemException;
import uk.co.kayratech.m2m.platform.dao.BaseDao;
import uk.co.kayratech.m2m.platform.model.BaseEntity;
import uk.co.kayratech.m2m.platform.model.support.BaseSupport;

@Repository
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class BaseTxSupportImpl<T extends BaseEntity> implements BaseTxSupport<T> {

	@PersistenceContext
	private EntityManager em;
	
	private BaseDao<T, String> dao;
	private BaseSupport<T> support;

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
	
	public T getPopulatedInstanceToBeSaved(Class<T> clazz) {
		try {
			T objectToReturn = clazz.newInstance();
			support.populateObjectToBeSaved(objectToReturn);
			return objectToReturn;
		}
		catch (InstantiationException | IllegalAccessException e) {
			M2MSystemException sysEx = new M2MSystemException(
					"Exception received when instantiating class " + clazz.getSimpleName());
			sysEx.setStackTrace(e.getStackTrace());
			throw sysEx;
		}
	}

	protected BaseDao<T, String> getDao() {
		return dao;
	}

	protected void setDao(BaseDao<T, String> dao) {
		this.dao = dao;
	}

	protected BaseSupport<T> getSupport() {
		return support;
	}

	protected void setSupport(BaseSupport<T> support) {
		this.support = support;
	}

	protected EntityManager getEm() {
		return em;
	}
}
