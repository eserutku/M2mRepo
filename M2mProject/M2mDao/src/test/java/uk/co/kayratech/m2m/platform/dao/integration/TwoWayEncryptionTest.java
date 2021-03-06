package uk.co.kayratech.m2m.platform.dao.integration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import uk.co.kayratech.m2m.platform.common.context.InheritableThreadLocalContext;
import uk.co.kayratech.m2m.platform.dao.UserDao;
import uk.co.kayratech.m2m.platform.model.User;
import uk.co.kayratech.m2m.platform.model.factory.UserFactory;

// This NOT a JUnit test - creates a user persisted in DB. User is NOT deleted at the end of the test
public class TwoWayEncryptionTest {

	public void testTwoWayEncryptionIsPossible() {
		InheritableThreadLocalContext.instance.get().setUsername("A_USER");
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"/META-INF/spring/m2mDaoAppContext.xml");
		UserDao dao = ctx.getBean(UserDao.class);
		UserFactory support = ctx.getBean(UserFactory.class);
		User user = support.getPopulatedInstanceWithSystemFields(User.class);
		user.setTwoWayEncryptedData("pass");
		User returnedUser = dao.save(user);
		
		User savedUser = dao.findOne(returnedUser.getId());
		System.out.println(savedUser.getTwoWayEncryptedData());
	}

	public static void main(String[] args) {
		TwoWayEncryptionTest test = new TwoWayEncryptionTest();
		test.testTwoWayEncryptionIsPossible();
	}

}
