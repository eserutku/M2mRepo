package uk.co.kayratech.m2m.platform.txlogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import uk.co.kayratech.m2m.platform.dao.LovTypeDao;
import uk.co.kayratech.m2m.platform.model.lov.LovType;

@Component
public class LovTypeTxLogicImpl<T extends LovType> extends BaseTxLogicImpl<LovType> implements
		LovTypeTxLogic {
	@Autowired
	private LovTypeDao dao;
	
	@Override
	protected JpaRepository<LovType, String> getDao() {
		return dao;
	}

}
