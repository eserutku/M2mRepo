package uk.co.kayratech.m2m.platform.dao;

import java.util.List;

public interface AppConfigDao extends BaseDao {

	public List<AppConfig> findByExample(AppConfig configParam);
	
	public String findConfigValue(AppConfig configParam);

}
