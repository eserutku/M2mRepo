package uk.co.kayratech.m2m.platform.txlogic;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.kayratech.m2m.platform.dao.BaseDao;
import uk.co.kayratech.m2m.platform.dao.UserDao;
import uk.co.kayratech.m2m.platform.model.User;

public class UserTxLogicImpl<T extends User> extends BaseTxLogicImpl<User> implements UserTxLogic<User> {
	@Autowired
	private UserDao dao;
	@Override
	protected BaseDao<User, String> getDao() {
		return dao;
	}

}
