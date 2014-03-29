package uk.co.kayratechm2m.platform.txlogic;

import org.slf4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import uk.co.kayratech.m2m.platform.common.log.InjectLogger;
import uk.co.kayratech.m2m.platform.model.BaseEntity;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class BaseTxLogicImpl<T extends BaseEntity> implements BaseTxLogic<T> {
	@InjectLogger
	protected Logger logger;
}
