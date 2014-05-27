package uk.co.kayratech.m2m.platform.txlogic;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.kayratech.m2m.platform.dao.BaseDao;
import uk.co.kayratech.m2m.platform.dao.LovTypeDao;
import uk.co.kayratech.m2m.platform.model.lov.LovType;

public class LovTypeTxLogicImpl<T extends LovType> extends BaseTxLogicImpl<LovType> implements
		LovTypeTxLogic<LovType> {
	@Autowired
	private LovTypeDao dao;
	
	@Override
	protected BaseDao<LovType, String> getDao() {
		return dao;
	}

}
