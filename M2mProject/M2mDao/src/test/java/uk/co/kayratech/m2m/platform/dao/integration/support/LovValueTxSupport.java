package uk.co.kayratech.m2m.platform.dao.integration.support;

import uk.co.kayratech.m2m.platform.model.lov.LovValue;

public interface LovValueTxSupport extends BaseTxSupport<LovValue> {
	public LovValue findAny(Class<? extends LovValue> lovValueClass);
}
