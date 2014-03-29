package uk.co.kayratechm2m.platform.txlogic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.kayratech.m2m.platform.dao.LovValueDao;
import uk.co.kayratech.m2m.platform.model.lov.LovValue;

public class LovValueTxLogicImpl<T extends LovValue> extends BaseTxLogicImpl<LovValue> implements
		LovValueTxLogic<LovValue> {

	@Autowired
	private LovValueDao lovValueDao;
	
	@Override
	public List<LovValue> getAllLovValues() {
		logger.info("Getting all LovValue objects");
		List<LovValue> allLovValues = lovValueDao.findAll();
		logger.info("Returning " + allLovValues.size() + " objects");
		return allLovValues;
	}

}
