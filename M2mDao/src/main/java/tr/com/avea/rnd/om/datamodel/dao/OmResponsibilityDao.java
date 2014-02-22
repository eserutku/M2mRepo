package tr.com.avea.rnd.om.datamodel.dao;

import java.util.List;


public interface OmResponsibilityDao extends BaseDao{

	public List<OmResponsibility> selectUserResponsibilitiesWithViews(OmUser omUser);
	
	public List<OmResponsibility> selectMatchedResponsibilities(OmResponsibility omResponsibility);
	
	public List<OmResponsibility> selectdefaultResponsibilities(List<AppConfig> appConfigList);
}
