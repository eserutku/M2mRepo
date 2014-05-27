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
public abstract class BaseTxSupportImpl<T extends BaseEntity> implements BaseTxSupport<T> {

	@PersistenceContext
	private EntityManager em;
	
	public BaseTxSupportImpl() {
		
	}

	@Override
	public T save(T objectToSave) {
		return getDao().save(objectToSave);
	}

	@Override
	public List<Number> getRevisionsForObject(Class<T> clazz, String primaryKey) {
		return getDao().getRevisionsForObject(clazz, primaryKey);
	}

	@Override
	public void delete(T objectToDelete) {
		getDao().delete(objectToDelete);
	}
	
	@Override
	public void deleteAll() {
		getDao().deleteAll();
	}
	
	@Override
	public Page<T> findAll(Pageable page) {
		return getDao().findAll(page);
	}
	
	public T getPopulatedInstanceToBeSaved(Class<T> clazz) {
		try {
			T objectToReturn = clazz.newInstance();
			getSupport().populateObjectToBeSaved(objectToReturn);
			return objectToReturn;
		}
		catch (InstantiationException | IllegalAccessException e) {
			M2MSystemException sysEx = new M2MSystemException(
					"Exception received when instantiating class " + clazz.getSimpleName());
			sysEx.setStackTrace(e.getStackTrace());
			throw sysEx;
		}
	}

	// Used in setting the dao and support objects in Base Support class implementation
	protected abstract BaseDao<T, String> getDao();
	protected abstract BaseSupport<T> getSupport();

	protected EntityManager getEm() {
		return em;
	}
}
