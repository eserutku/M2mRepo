package tr.com.avea.rnd.om.datamodel.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.Example;

import uk.co.kayratech.m2m.platform.dao.AppConfigDao;

@Repository("appConfigDao")
public class AppConfigDaoImpl extends BaseDaoImpl implements AppConfigDao {

	private static final long serialVersionUID = -6043624429736378250L;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<AppConfig> findByExample(AppConfig configParam) {
		Example example = Example.create(configParam);
		example.enableLike();
		setIgnoreOnSysColumns(example, getCommonSysAttributes());
		@SuppressWarnings("unchecked")
		List<AppConfig> resultset = (List<AppConfig>)getSession().createCriteria(AppConfig.class)
			.add(example)
			.list();
		return resultset;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public String findConfigValue(AppConfig configParam) {
		Example example = Example.create(configParam);
		example.enableLike();
		@SuppressWarnings("unchecked")
		List<AppConfig> appConfigs = (List<AppConfig>)getSession().createCriteria(AppConfig.class)
			.add(example)
			.list();
		if (appConfigs.size() == 1) {
			return appConfigs.get(0).getConfigValue();
		}
		return null;
	}
	
}