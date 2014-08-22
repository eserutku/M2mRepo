package uk.co.kayratech.m2m.platform.txlogic;

import java.util.List;

import uk.co.kayratech.m2m.platform.model.lov.LovValue;

public interface LovValueTxLogic extends BaseTxLogic<LovValue> {
	public List<LovValue> findAllLovValues();
}
