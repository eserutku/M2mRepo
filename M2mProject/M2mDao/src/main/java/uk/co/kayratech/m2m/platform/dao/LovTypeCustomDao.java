package uk.co.kayratech.m2m.platform.dao;

import java.util.List;

import uk.co.kayratech.m2m.platform.common.exceptions.M2MBusinessException;
import uk.co.kayratech.m2m.platform.model.lov.LovType;
import uk.co.kayratech.m2m.platform.model.lov.LovValue;

public interface LovTypeCustomDao {

	public <T extends LovType> List<T> findLovTypeByLovValue(LovValue lovaValue)
			throws M2MBusinessException;
}
