package uk.co.kayratech.m2m.platform.dao.integration;

import uk.co.kayratech.m2m.platform.dao.BaseDao;
import uk.co.kayratech.m2m.platform.dao.impl.BaseTxSupportImpl;
import uk.co.kayratech.m2m.platform.model.User;

public class UserTxSupportImpl extends BaseTxSupportImpl<User> implements UserTxSupport {

	public UserTxSupportImpl(BaseDao<User, String> dao) {
		super(dao);
	}
}
