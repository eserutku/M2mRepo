package tr.com.avea.rnd.om.datamodel.dao;

import java.util.List;

public interface AppConfigDao extends BaseDao {

	public List<AppConfig> findByExample(AppConfig configParam);
	
	public String findConfigValue(AppConfig configParam);

}
