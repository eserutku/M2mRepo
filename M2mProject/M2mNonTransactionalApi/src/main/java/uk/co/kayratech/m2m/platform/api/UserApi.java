package uk.co.kayratech.m2m.platform.api;

import uk.co.kayratech.m2m.platform.common.exceptions.M2MBusinessException;
import uk.co.kayratech.m2m.platform.common.exceptions.M2MSystemException;
import uk.co.kayratech.m2m.platform.model.User;

public interface UserApi extends BaseApi<User> {
	
	User createUser(User user) throws M2MSystemException, M2MBusinessException;
}
