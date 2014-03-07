package uk.co.kayratech.m2m.platform.dao.impl;

import java.util.List;

import uk.co.kayratech.aop.annotations.NoAutoLogging;
import uk.co.kayratech.m2m.platform.common.exceptions.M2MBusinessException;
import uk.co.kayratech.m2m.platform.dao.LovTypeCustomDao;
import uk.co.kayratech.m2m.platform.model.lov.LovType;
import uk.co.kayratech.m2m.platform.model.lov.LovValue;

@NoAutoLogging
public class LovTypeDaoImpl implements LovTypeCustomDao {

	@Override
	public <T extends LovType> List<T> findLovTypeByLovValue(LovValue omLovName)
			throws M2MBusinessException {
		// TODO Implement the method
		return null;
	}
}
