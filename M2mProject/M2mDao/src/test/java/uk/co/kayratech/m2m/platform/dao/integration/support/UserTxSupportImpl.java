package uk.co.kayratech.m2m.platform.dao.integration.support;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.co.kayratech.m2m.platform.dao.UserDao;
import uk.co.kayratech.m2m.platform.model.User;

@Component
public class UserTxSupportImpl extends BaseTxSupportImpl<User> implements UserTxSupport {

	@Autowired
	private UserDao dao;

	@PostConstruct
	public void postConstruct() {
		super.setDao(dao);
	}
}
