package uk.co.kayratech.m2m.platform.txlogic;

import java.util.List;

import uk.co.kayratech.m2m.platform.model.BaseEntity;

public interface BaseTxLogic<T extends BaseEntity> {
	T findObject(String objectId);
	List<T> findAllObjects();
}
