package tr.com.avea.rnd.om.datamodel.dao;

import java.util.List;

public interface OmCustomerGroupDao extends BaseDao {
	
	public List<OmCustomerGroup> getCustomerGroupList() throws OmException;
	
	public OmCustomerTariffGroup customerGroupLookUp(OmCustomerGroup omCustomerGroup) throws OmException;
	
}