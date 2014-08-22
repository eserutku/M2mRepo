package uk.co.kayratech.m2m.platform.model.factory;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.co.kayratech.m2m.platform.dao.LovTypeDao;
import uk.co.kayratech.m2m.platform.dao.LovValueDao;
import uk.co.kayratech.m2m.platform.model.User;
import uk.co.kayratech.m2m.platform.model.lov.LovType;
import uk.co.kayratech.m2m.platform.model.lov.UserType;

@Component
public class UserFactory extends BaseFactory<User> {

	@Autowired
	private LovTypeDao lovTypeDao;
	@Autowired
	private LovValueDao lovValueDao;

	@Override
	public void populateObject(User user) {
		user.setUsername(UUID.randomUUID().toString());
		LovType lovType = lovTypeDao.findLovTypeByLovType("UserType");
		UserType userType = (UserType) lovValueDao.findByLovTypeAndLic(lovType, "STANDARD")
				.get(0);
		user.setUserType(userType);
		user.setTwoWayEncryptedData("encryptedData");
	}
}
