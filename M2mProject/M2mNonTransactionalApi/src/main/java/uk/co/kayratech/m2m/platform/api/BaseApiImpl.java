package uk.co.kayratech.m2m.platform.api;

import uk.co.kayratech.m2m.platform.model.BaseEntity;
import uk.co.kayratech.m2m.platform.txlogic.BaseTxLogic;

public abstract class BaseApiImpl<T extends BaseEntity> implements BaseApi<T> {
	protected abstract BaseTxLogic<T> getTxLogic();
}
