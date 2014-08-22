package uk.co.kayratech.m2m.platform.dao.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.Serializable;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import uk.co.kayratech.m2m.platform.dao.AuditDao;
import uk.co.kayratech.m2m.platform.dao.UserDao;
import uk.co.kayratech.m2m.platform.model.User;
import uk.co.kayratech.m2m.platform.model.factory.UserFactory;

public class UserDaoIntegrationTest extends M2mDaoBaseIntegrationTest {

	@Autowired
	private UserDao classUnderTest;
	@Autowired
	private UserFactory txSupport;
	@Autowired
	private AuditDao<User, Serializable> auditDao;
	
	@Before
	public void before() {
	}

	@Test
	public void testPersistingUserPopulatesCreatedAndLastUpdateFields() {
		User user = txSupport.getPopulatedInstanceWithSystemFields(User.class);
		assertNull(user.getCreatedDate());
		assertNull(user.getCreatedBy());
		assertNull(user.getLastModifiedDate());
		assertNull(user.getLastModifiedBy());

		classUnderTest.save(user);

		assertNotNull(user.getCreatedDate());
		assertNotNull(user.getCreatedBy());
		assertNotNull(user.getLastModifiedDate());
		assertNotNull(user.getLastModifiedBy());
	}

	@Test
	@Transactional(propagation = Propagation.NEVER)
	public void testUpdatingUserPopulatesLastUpdateFieldsButNotCreatedFields()
			throws InterruptedException {
		User user = txSupport.getPopulatedInstanceWithSystemFields(User.class);
		classUnderTest.save(user);
		
		DateTime createAndUpdateDate = user.getLastModifiedDate();
		// Make sure new update date is later than current
		Thread.sleep(1000);
		user.setUsername(user.getUsername() + "2");

		user = classUnderTest.save(user);

		assertEquals(createAndUpdateDate, user.getCreatedDate());
		assertNotEquals(createAndUpdateDate, user.getLastModifiedDate());
	}

	@Test
	@Transactional(propagation = Propagation.NEVER)
	public void testUserIsAudited() {
		User user = txSupport.getPopulatedInstanceWithSystemFields(User.class);
		user = classUnderTest.save(user);
		List<Number> revisions = auditDao.getRevisionsForObject(User.class, user.getTechnicalId());
		int lastRevision = revisions.get(revisions.size() - 1).intValue();

		user.setUsername(user.getUsername() + "2");
		user = classUnderTest.save(user);

		List<Number> revisionsAfterUpdate = auditDao.getRevisionsForObject(User.class,
				user.getTechnicalId());
		int lastRevisionAfterUpdate = revisionsAfterUpdate.get(revisionsAfterUpdate.size() - 1)
				.intValue();

		assertEquals(lastRevision + 1, lastRevisionAfterUpdate);
	}

	@Test
	public void testFindUserByUsername() {
		String usernameToSearch = "FIND_ME";
		String techIdToFind;
		User dontFindMe = txSupport.getPopulatedInstanceWithSystemFields(User.class);
		classUnderTest.save(dontFindMe);
		User dontFindMeEither = txSupport.getPopulatedInstanceWithSystemFields(User.class);
		dontFindMeEither.setUsername(dontFindMe.getUsername() + "2");
		classUnderTest.save(dontFindMeEither);
		User findMe = txSupport.getPopulatedInstanceWithSystemFields(User.class);
		findMe.setUsername(usernameToSearch);
		classUnderTest.save(findMe);
		techIdToFind = findMe.getTechnicalId();

		User userFound = classUnderTest.findByUsername(usernameToSearch);

		assertEquals(techIdToFind, userFound.getTechnicalId());
		assertEquals(usernameToSearch, userFound.getUsername());
	}
}
