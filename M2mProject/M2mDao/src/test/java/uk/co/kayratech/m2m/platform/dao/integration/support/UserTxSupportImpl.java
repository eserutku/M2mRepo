package uk.co.kayratech.m2m.platform.dao.integration.support;

import static org.junit.Assert.*;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.co.kayratech.m2m.platform.dao.LovTypeDao;
import uk.co.kayratech.m2m.platform.dao.LovValueDao;
import uk.co.kayratech.m2m.platform.dao.UserDao;
import uk.co.kayratech.m2m.platform.model.User;
import uk.co.kayratech.m2m.platform.model.lov.LovType;
import uk.co.kayratech.m2m.platform.model.lov.LovValue;
import uk.co.kayratech.m2m.platform.model.lov.UserType;
import uk.co.kayratech.m2m.platform.model.support.UserSupport;

@Component
public class UserTxSupportImpl extends BaseTxSupportImpl<User> implements UserTxSupport {

	@Autowired
	private UserDao dao;
	@Autowired
	private LovTypeDao lovTypeDao;
	@Autowired
	private LovValueDao lovValueDao;
	@Autowired
	private UserSupport support;

	@PostConstruct
	public void postConstruct() {
		super.setDao(dao);
		super.setSupport(support);
	}
	
	@Override
	public User getPopulatedInstanceToBeSaved(Class<User> clazz) {
		User user = super.getPopulatedInstanceToBeSaved(clazz);
		
		LovType userTypeLovType = lovTypeDao.findLovTypeByLovType(UserType.class.getSimpleName());
		assertNotNull(userTypeLovType);
		
		LovValue lovValue = lovValueDao.findLovValueByLovTypeAndLic(userTypeLovType, "STANDARD").get(0);
		assertNotNull(lovValue);
		assertEquals(UserType.class, lovValue.getClass());
		
		UserType userType = (UserType)lovValue;
		user.setUserType(userType);
		
		return user;
	}
}
