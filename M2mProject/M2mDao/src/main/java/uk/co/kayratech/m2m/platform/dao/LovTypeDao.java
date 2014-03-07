package uk.co.kayratech.m2m.platform.dao;

import java.util.List;

import uk.co.kayratech.m2m.platform.model.lov.LovType;

public interface LovTypeDao extends BaseDao<LovType, String>, LovTypeCustomDao {
	public <T extends LovType> List<T> findLovTypeByFrozen(boolean frozen);
}
