package tr.com.avea.rnd.om.datamodel.dao.impl;

import java.util.ArrayList;
import java.util.List;

import tr.com.avea.rnd.om.datamodel.dao.OmAccountDao;

@Service("omAccountDao")
@Scope("singleton")
public class OmAccountDaoImpl extends BaseDaoImpl implements OmAccountDao {

	@Autowired
	private SearchCustomerGateway searchCustomerGateway;
	@Autowired
	private RetrieveCustomerGateway retrieveCustomerGateway;
	@Autowired
	private GatewayService gatewayService;

	@Override
	public OmAccount findByPrimaryKey(String primaryKey) throws OmException {
		RetrieveCustomerRequest retrieveCustomerRequest = CustomerConverter
				.createRetrieveCustomerRequestWithAccountId(primaryKey);
		retrieveCustomerRequest.setRequestHeader(gatewayService.createRequestHeader());

		RetrieveCustomerReply retrieveCustomerReply = retrieveCustomerGateway
				.retrieveCustomer(retrieveCustomerRequest);

		CustomerConverter.validateRetrieveCustomerReply(retrieveCustomerRequest, retrieveCustomerReply);

		return AccountConverter.createOmAccountWithCustomerAccount(retrieveCustomerReply.getResponseBody()
				.getCustomerAccount());
	}

	@Override
	public List<OmAccount> findByExample(OmAccount omAccount) throws OmException {
		SearchCustomerRequest searchCustomerRequest = CustomerConverter
				.createSearchCustomerRequestWithOmAccount(omAccount);
		searchCustomerRequest.setRequestHeader(gatewayService.createRequestHeader());

		SearchCustomerReply searchCustomerReply = null;
		try {
			searchCustomerReply = searchCustomerGateway.searchCustomer(searchCustomerRequest);
		}
		catch (SOADataNotFoundException e) {
			return new ArrayList<OmAccount>();
		}
		catch (SOATooManyRowsException e) {
			throw new TooManyRecordsFoundException("Too many accounts were retrieved " + e.getMessage());

		}

		CustomerConverter.validateSearchCustomerReply(searchCustomerRequest, searchCustomerReply);

		return AccountConverter.createOmAccountListWithCustomerAccountList(searchCustomerReply
				.getResponseBody().getListOfCustomerAccount().getCustomerAccount());
	}

}