package uk.co.kayratech.m2m.platform.dao.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import uk.co.kayratech.m2m.platform.dao.UserDao;
import uk.co.kayratech.m2m.platform.model.User;
import uk.co.kayratech.m2m.platform.model.support.UserSupport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationConfiguration.xml" })
public class UserDaoIntegrationTest extends M2mDaoBaseIntegrationTest {

	@Autowired
	private UserDao classUnderTest;
	private UserSupport support = new UserSupport();
	
	@Before
	public void before()
	{
		classUnderTest.deleteAll();
	}
	
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
		User user = support.getPopulatedInstanceToBeSaved(User.class);
		classUnderTest.save(user);
		// Make sure new update date is later than current
		long tenSecondsAgo = System.currentTimeMillis() - 10000;
		user.setLastUpdate(new DateTime(tenSecondsAgo));
		user.setCreated(new DateTime(tenSecondsAgo));
		DateTime createAndUpdateDate = user.getLastUpdate();
		
		user = classUnderTest.save(user);
		
		assertEquals(createAndUpdateDate, user.getCreated());
		assertNotEquals(createAndUpdateDate, user.getLastUpdate());
	}

	@Test
//	@Transactional
	public void testUserAuditCanBeFetched() {
		User user = support.getPopulatedInstanceToBeSaved(User.class);
		user = classUnderTest.save(user);
		user.setUsername(user.getUsername()+ "2");
		
		user = classUnderTest.save(user);
		
		classUnderTest.delete(user);
	}

	@Test
	public void testFindUserByUsername() {
		String usernameToSearch = "FIND_ME";
		String techIdToFind;
		User dontFindMe = support.getPopulatedInstanceToBeSaved(User.class);
		classUnderTest.save(dontFindMe);
		User dontFindMeEither = support.getPopulatedInstanceToBeSaved(User.class);
		dontFindMeEither.setUsername(dontFindMeEither.getUsername() + "2");
		classUnderTest.save(dontFindMeEither);
		User findMe = support.getPopulatedInstanceToBeSaved(User.class);
		findMe.setUsername(usernameToSearch);
		classUnderTest.save(findMe);
		techIdToFind = findMe.getTechnicalId();
		
		User userFound = classUnderTest.findUserByUsername(usernameToSearch);
		
		assertEquals(techIdToFind, userFound.getTechnicalId());
		assertEquals(usernameToSearch, userFound.getUsername());
	}
}
