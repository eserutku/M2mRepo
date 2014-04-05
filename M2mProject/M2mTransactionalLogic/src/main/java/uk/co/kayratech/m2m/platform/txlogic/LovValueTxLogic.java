package uk.co.kayratech.m2m.platform.txlogic;

import java.util.List;

import uk.co.kayratech.m2m.platform.model.lov.LovValue;

public interface LovValueTxLogic<T extends LovValue> extends BaseTxLogic<LovValue> {
	public List<LovValue> getAllLovValues();
}
