package uk.co.kayratech.m2m.platform.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import uk.co.kayratech.m2m.platform.dao.BaseDao;
import uk.co.kayratech.m2m.platform.dao.UserDao;
import uk.co.kayratech.m2m.platform.model.BaseEntity;

@Repository
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//public abstract class BaseTxSupportImpl<T extends BaseEntity> implements BaseTxSupport<T> {
public class BaseTxSupportImpl<T extends BaseEntity> implements BaseTxSupport<T> {

//	@PersistenceContext
//	private EntityManager em;
	
	@Autowired
	private UserDao userDao;
	
	private BaseDao<T, String> dao;
	
	public BaseTxSupportImpl() {
//		super();
	}

	public BaseTxSupportImpl(BaseDao<T, String> dao) {
		this.dao = dao;
	}

	@Override
	public void deleteAllTableData() {
//		String baseHql = "DELETE FROM ";
//		
//		String deleteUsers = baseHql + User.class.getSimpleName();
//		em.createQuery(deleteUsers).executeUpdate();
		userDao.deleteAllInBatch();
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
}
