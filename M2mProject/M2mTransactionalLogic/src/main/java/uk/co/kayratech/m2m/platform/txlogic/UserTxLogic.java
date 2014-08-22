package uk.co.kayratech.m2m.platform.txlogic;

import uk.co.kayratech.m2m.platform.common.exceptions.M2MBusinessException;
import uk.co.kayratech.m2m.platform.model.User;

public interface UserTxLogic extends BaseTxLogic<User> {
	User createUser(User user) throws M2MBusinessException;
}
