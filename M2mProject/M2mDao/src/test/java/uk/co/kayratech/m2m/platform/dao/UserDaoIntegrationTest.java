package uk.co.kayratech.m2m.platform.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.co.kayratech.m2m.platform.model.User;
import uk.co.kayratech.m2m.platform.model.support.UserSupport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/META-INF/spring/applicationConfiguration.xml"})
public class UserDaoIntegrationTest extends M2mDaoBaseIntegrationTest {

	@Autowired
	private UserDao classUnderTest;
	private UserSupport support = new UserSupport();
	
	@Test
	public void testPersistingUserPopulatesCreatedAndLastUpdateFields() {
		User user = support.getPopulatedInstanceToBeSaved(User.class);
		assertNull(user.getCreated());
		assertNull(user.getCreatedBy());
		assertNull(user.getLastUpdate());
		assertNull(user.getLastUpdateBy());
		
		classUnderTest.save(user);
		
		assertNotNull(user.getCreated());
		assertNotNull(user.getCreatedBy());
		assertNotNull(user.getLastUpdate());
		assertNotNull(user.getLastUpdateBy());
	}

	@Test
	public void testUpdatingUserPopulatesLastUpdateFieldsButNotCreatedFields() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testUserAuditCanBeFetched() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testFindUserByUsername() {
		fail("Not yet implemented");
	}
}
