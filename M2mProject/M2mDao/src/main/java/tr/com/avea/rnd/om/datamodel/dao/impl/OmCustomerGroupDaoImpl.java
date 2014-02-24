package tr.com.avea.rnd.om.datamodel.dao.impl;

import java.util.List;

import tr.com.avea.rnd.om.datamodel.dao.OmCustomerGroupDao;

@Repository("omCustomerGroupDao")
public class OmCustomerGroupDaoImpl extends BaseDaoImpl implements OmCustomerGroupDao {
	
	@Autowired
	private CustomerGroupLookUpGateway customerGroupLookUpGateway;
	@Autowired
	private GetCustomerGroupListGateway getCustomerGroupListGateway;
	@Autowired
	private GatewayService gatewayService;
	
	@Override
	public List<OmCustomerGroup> getCustomerGroupList() throws OmException {
		GetCustomerGroupListRequest getCustomerGroupListRequest = CustomerGroupConverter.createGetCustomerGroupListRequest();
		getCustomerGroupListRequest.setRequestHeader(gatewayService.createRequestHeader());
		
		GetCustomerGroupListReply getCustomerGroupListReply = getCustomerGroupListGateway.getCustomerGroupList(getCustomerGroupListRequest);
		
		CustomerGroupConverter.validateGetCustomerGroupListReply(getCustomerGroupListRequest, getCustomerGroupListReply);
		
		return CustomerGroupConverter.createOmCustomerGroupListWithGetCustomerGroupListReply(getCustomerGroupListReply);
	}
	
	@Override
	public OmCustomerTariffGroup customerGroupLookUp(OmCustomerGroup omCustomerGroup) throws OmException {
		CustomerGroupLookUpRequest customerGroupLookUpRequest = CustomerGroupConverter.createCustomerGroupLookUpRequestWithOmCustomerGroup(omCustomerGroup);
		customerGroupLookUpRequest.setRequestHeader(gatewayService.createRequestHeader());
		
		CustomerGroupLookUpReply customerGroupLookUpReply = customerGroupLookUpGateway.customerGroupLookUp(customerGroupLookUpRequest);
		
		CustomerGroupConverter.validateCustomerGroupLookUpReply(customerGroupLookUpRequest, customerGroupLookUpReply);
		
		return CustomerGroupConverter.createOmCustomerTariffGroupWithCustomerGroupLookUpReply(customerGroupLookUpReply);
	}
}