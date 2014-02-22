package tr.com.avea.rnd.om.datamodel.dao;

import java.util.List;

public interface OmPttAddressDao extends BaseDao {
	
	public List<OmPttAddress> getCityList() throws OmException;
	
	public List<OmPttAddress> getDistrictListForCity(OmPttAddress omPttAddress) throws OmException;
	
	public List<OmPttAddress> getNeighbourhoodListForDistrict(OmPttAddress omPttAddress) throws OmException;
	
	public OmPttAddress getPostCodeForNeighbourhood(OmPttAddress omPttAddress) throws OmException;

}