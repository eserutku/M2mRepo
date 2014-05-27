package uk.co.kayratech.m2m.platform.dao;

import java.util.List;

import uk.co.kayratech.m2m.platform.model.lov.LovType;
import uk.co.kayratech.m2m.platform.model.lov.LovValue;

public interface LovValueDao extends BaseDao<LovValue, String>, LovValueCustomDao {
	public <T extends LovValue> List<T> findLovTypeByFrozen(boolean frozen);
	public <T extends LovValue> List<T> findLovValueByLovTypeAndLic(LovType lovType, String lic);
}
