package uk.co.kayratech.m2m.platform.txlogic;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import uk.co.kayratech.m2m.platform.common.log.InjectLogger;
import uk.co.kayratech.m2m.platform.model.BaseEntity;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public abstract class BaseTxLogicImpl<T extends BaseEntity> implements BaseTxLogic<T> {
	@InjectLogger
	protected Logger logger;
	
	@Override
	public T findObject(String objectId) {
		return getDao().findOne(objectId);
	}
	
	@Override
	public List<T> findAllObjects() {
		return getDao().findAll();
	}
	
	protected abstract JpaRepository<T, String> getDao();
}
