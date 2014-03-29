package uk.co.kayratech.m2m.platform.dao.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import uk.co.kayratech.m2m.platform.dao.UserDao;
import uk.co.kayratech.m2m.platform.dao.integration.support.UserTxSupport;
import uk.co.kayratech.m2m.platform.model.User;

public class UserDaoIntegrationTest extends M2mDaoBaseIntegrationTest {

	@Autowired
	private UserDao classUnderTest;
	@Autowired
	private UserTxSupport txSupport;

	@Before
	public void before() {
	}

	@Test
	public void testPersistingUserPopulatesCreatedAndLastUpdateFields() {
		User user = txSupport.getPopulatedInstanceToBeSaved(User.class);
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
	@Transactional(propagation = Propagation.NEVER)
	public void testUpdatingUserPopulatesLastUpdateFieldsButNotCreatedFields()
			throws InterruptedException {
		User user = txSupport.getPopulatedInstanceToBeSaved(User.class);
		classUnderTest.save(user);
		
		DateTime createAndUpdateDate = user.getLastUpdate();
		// Make sure new update date is later than current
		Thread.sleep(1000);
		user.setUsername(user.getUsername() + "2");

		user = classUnderTest.save(user);

		assertEquals(createAndUpdateDate, user.getCreated());
		assertNotEquals(createAndUpdateDate, user.getLastUpdate());
	}

	@Test
	@Transactional(propagation = Propagation.NEVER)
	public void testUserIsAudited() {
		User user = txSupport.getPopulatedInstanceToBeSaved(User.class);
		user = txSupport.save(user);
		List<Number> revisions = txSupport.getRevisionsForObject(User.class, user.getTechnicalId());
		int lastRevision = revisions.get(revisions.size() - 1).intValue();

		user.setUsername(user.getUsername() + "2");
		user = txSupport.save(user);

		List<Number> revisionsAfterUpdate = txSupport.getRevisionsForObject(User.class,
				user.getTechnicalId());
		int lastRevisionAfterUpdate = revisionsAfterUpdate.get(revisionsAfterUpdate.size() - 1)
				.intValue();

		assertEquals(lastRevision + 1, lastRevisionAfterUpdate);
	}

	@Test
	public void testFindUserByUsername() {
		String usernameToSearch = "FIND_ME";
		String techIdToFind;
		User dontFindMe = txSupport.getPopulatedInstanceToBeSaved(User.class);
		classUnderTest.save(dontFindMe);
		User dontFindMeEither = txSupport.getPopulatedInstanceToBeSaved(User.class);
		dontFindMeEither.setUsername(dontFindMe.getUsername() + "2");
		classUnderTest.save(dontFindMeEither);
		User findMe = txSupport.getPopulatedInstanceToBeSaved(User.class);
		findMe.setUsername(usernameToSearch);
		classUnderTest.save(findMe);
		techIdToFind = findMe.getTechnicalId();

		User userFound = classUnderTest.findUserByUsername(usernameToSearch);

		assertEquals(techIdToFind, userFound.getTechnicalId());
		assertEquals(usernameToSearch, userFound.getUsername());
	}
}
