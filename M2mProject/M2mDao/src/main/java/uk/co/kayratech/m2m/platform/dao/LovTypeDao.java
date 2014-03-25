package uk.co.kayratech.m2m.platform.dao;

import java.util.List;

import uk.co.kayratech.m2m.platform.model.lov.LovType;
import uk.co.kayratech.m2m.platform.model.lov.LovValue;

public interface LovTypeDao extends BaseDao<LovType, String>, LovTypeCustomDao {
	public List<LovType> findLovTypeByValues(LovValue values);
}
