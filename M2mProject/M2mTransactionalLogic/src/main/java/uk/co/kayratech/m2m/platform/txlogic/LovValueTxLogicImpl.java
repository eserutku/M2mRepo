package uk.co.kayratech.m2m.platform.txlogic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.co.kayratech.m2m.platform.dao.BaseDao;
import uk.co.kayratech.m2m.platform.dao.LovValueDao;
import uk.co.kayratech.m2m.platform.model.lov.LovValue;

@Component
public class LovValueTxLogicImpl<T extends LovValue> extends BaseTxLogicImpl<LovValue> implements
		LovValueTxLogic<LovValue> {

	@Autowired
	private LovValueDao dao;
	
	@Override
	public List<LovValue> findAllLovValues() {
		logger.info("Getting all LovValue objects");
		List<LovValue> allLovValues = findAllObjects();
		logger.info("Returning " + allLovValues.size() + " objects");
		return allLovValues;
	}

	@Override
	protected BaseDao<LovValue, String> getDao() {
		return dao;
	}

}
