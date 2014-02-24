package tr.com.avea.rnd.om.datamodel.dao.impl;

import java.util.List;

import tr.com.avea.rnd.om.datamodel.dao.OmPttAddressDao;

@Repository("omPttAddressDao")
public class OmPttAddressDaoImpl extends BaseDaoImpl implements OmPttAddressDao {
	
	@Autowired
	private GetCityListGateway getCityListGateway;
	@Autowired
	private GetDistrictListForCityGateway getDistrictListForCityGateway;
	@Autowired
	private GetNeighbourhoodListForDistrictGateway getNeighbourhoodListForDistrictGateway;
	@Autowired
	private GetPostCodeForNeighbourhoodGateway getPostCodeForNeighbourhoodGateway;
	@Autowired
	private GatewayService gatewayService;
	
	@Override
	public List<OmPttAddress> getCityList() throws OmException {
		GetCityListRequest getCityListRequest = PttConverter.createGetCityListRequest();
		getCityListRequest.setRequestHeader(gatewayService.createRequestHeader());
		
		GetCityListReply getCityListReply = getCityListGateway.getCityList(getCityListRequest);
		
		PttConverter.validateGetCityListReply(getCityListRequest, getCityListReply);
		
		return PttConverter.createCityListWithGetCityListReply(getCityListReply);
	}
	
	@Override
	public List<OmPttAddress> getDistrictListForCity(OmPttAddress omPttAddress) throws OmException {
		GetDistrictListForCityRequest getDistrictListForCityRequest = PttConverter.createGetDistrictListForCityRequestWithOmPttAddress(omPttAddress);
		getDistrictListForCityRequest.setRequestHeader(gatewayService.createRequestHeader());
		
		GetDistrictListForCityReply getDistrictListForCityReply = getDistrictListForCityGateway.getDistrictListForCity(getDistrictListForCityRequest);
		
		PttConverter.validateGetDistrictListForCityReply(getDistrictListForCityRequest, getDistrictListForCityReply);
		
		return PttConverter.createDistrictListWithGetDistrictListForCityReply(getDistrictListForCityReply);
	}
	
	@Override
	public List<OmPttAddress> getNeighbourhoodListForDistrict(OmPttAddress omPttAddress) throws OmException {
		GetNeighbourhoodListForDistrictRequest getNeighbourhoodListForDistrictRequest = PttConverter.createGetNeighbourhoodListForDistrictRequestWithOmPttAddress(omPttAddress);
		getNeighbourhoodListForDistrictRequest.setRequestHeader(gatewayService.createRequestHeader());
		
		GetNeighbourhoodListForDistrictReply getNeighbourhoodListForDistrictReply = getNeighbourhoodListForDistrictGateway.getNeighbourhoodListForDistrict(getNeighbourhoodListForDistrictRequest);
		
		PttConverter.validateGetNeighbourhoodListForDistrictReply(getNeighbourhoodListForDistrictRequest, getNeighbourhoodListForDistrictReply);
		
		return PttConverter.createNeighbourhoodListWithGetNeighbourhoodListForDistrictReply(getNeighbourhoodListForDistrictReply);
	}
	
	@Override
	public OmPttAddress getPostCodeForNeighbourhood(OmPttAddress omPttAddress) throws OmException {
		GetPostCodeForNeighbourhoodRequest getPostCodeForNeighbourhoodRequest = PttConverter.createGetPostCodeForNeighbourhoodRequestWithOmPttAddress(omPttAddress);
		getPostCodeForNeighbourhoodRequest.setRequestHeader(gatewayService.createRequestHeader());
		
		GetPostCodeForNeighbourhoodReply getPostCodeForNeighbourhoodReply = getPostCodeForNeighbourhoodGateway.getPostCodeForNeighbourhood(getPostCodeForNeighbourhoodRequest);
		
		PttConverter.validateGetPostCodeForNeighbourhoodReply(getPostCodeForNeighbourhoodRequest, getPostCodeForNeighbourhoodReply);
		
		return PttConverter.createPostCodeWithGetPostCodeForNeighbourhoodReply(getPostCodeForNeighbourhoodReply);
	}

}