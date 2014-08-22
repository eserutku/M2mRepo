package uk.co.kayratech.m2m.platform.txlogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import uk.co.kayratech.m2m.platform.common.exceptions.M2MBusinessException;
import uk.co.kayratech.m2m.platform.dao.UserDao;
import uk.co.kayratech.m2m.platform.model.User;

@Component
public class UserTxLogicImpl<T extends User> extends BaseTxLogicImpl<User> implements
		UserTxLogic {
	
	@Autowired
	private UserDao dao;

	@Override
	protected JpaRepository<User, String> getDao() {
		return dao;
	}

	@Override
	public User createUser(User user) throws M2MBusinessException {
		return dao.save(user);
	}
}
