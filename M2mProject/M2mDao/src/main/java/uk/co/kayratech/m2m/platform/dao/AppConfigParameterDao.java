package uk.co.kayratech.m2m.platform.dao;

import uk.co.kayratech.m2m.platform.model.AppConfigParameter;

public interface AppConfigParameterDao extends BaseDao<AppConfigParameter, String>,
		AppConfigParameterCustomDao {
	
	public String findAppConfigParameterByParamValue(String paramValue);
}
