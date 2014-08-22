package uk.co.kayratech.m2m.platform.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import uk.co.kayratech.m2m.platform.common.exceptions.BusinessErrorCodes;
import uk.co.kayratech.m2m.platform.common.exceptions.M2MBusinessException;
import uk.co.kayratech.m2m.platform.common.exceptions.M2MSystemException;
import uk.co.kayratech.m2m.platform.model.User;
import uk.co.kayratech.m2m.platform.txlogic.BaseTxLogic;
import uk.co.kayratech.m2m.platform.txlogic.UserTxLogic;

@Component
public class UserApiImpl<T extends User> extends BaseApiImpl<User> implements UserApi {

	@Autowired
	private UserTxLogic txLogic;
	
	@Override
	@PreAuthorize("hasRole('ROLE_MODIFY_USER')")
	public User createUser(User user) throws M2MSystemException, M2MBusinessException {
		List<String> validationErrors = User.validate(user);
		if (validationErrors.size() != 0) {
			StringBuffer sb = new StringBuffer();
			for (String validationErr : validationErrors) {
				sb.append(validationErr);
				sb.append("\n");
			}
			throw new M2MBusinessException("Validation of user failed: " + sb.toString(),
					BusinessErrorCodes.VALIDATION_FAILED, new Object[] { sb.toString() });
		}
		
		return txLogic.createUser(user);
	}

	@Override
	protected BaseTxLogic<User> getTxLogic() {
		return txLogic;
	}

}
