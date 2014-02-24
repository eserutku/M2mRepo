package uk.co.kayratech.m2m.platform.model;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.kayratech.m2m.platform.common.context.InheritableThreadLocalContext;
import uk.co.kayratech.m2m.platform.common.i18n.MessageProvider;
import uk.co.kayratech.m2m.platform.model.constants.EntityConstraints;

public class UserTest {

	private static final Locale testLocale = Locale.ENGLISH;

	@BeforeClass
	public static void beforeClass() {
		InheritableThreadLocalContext.instance.get().setLocale(testLocale);
	}

	@Test
	public void testObjectWithNullTechnicalIdFailsEquals() {
		User user = getPopulatedUser();
		User anotherUser = getPopulatedUser();
		anotherUser.setTechnicalId(user.getTechnicalId());
		anotherUser.setIntegrationId(user.getIntegrationId());
		user.setTechnicalId(null);

		assertFalse(user.equals(anotherUser));
	}

	@Test
	public void testIfOtherObjectHasNullTechnicalIdEqualsFails() {
		User user = getPopulatedUser();
		User anotherUser = getPopulatedUser();
		anotherUser.setTechnicalId(user.getTechnicalId());
		anotherUser.setIntegrationId(user.getIntegrationId());
		anotherUser.setTechnicalId(null);

		assertFalse(user.equals(anotherUser));
	}

	@Test
	public void testIntegrationIdTooLongMessageWithMaxValueCanBeConstructed() {
		StringBuffer longIntegrationId = new StringBuffer();
		while (longIntegrationId.length() <= EntityConstraints.INTEGRATION_ID_MAX_SIZE) {
			longIntegrationId.append(UUID.randomUUID().toString());
		}
		int integrationIdSize = longIntegrationId.length();
		User user = getPopulatedUser();
		user.setIntegrationId(longIntegrationId.toString());

		List<String> validationMsgs = BaseEntity.validate(user);

		assertTrue(validationMsgs.size() == 1);

		String expectedMsg = MessageProvider.getMessage(
				EntityConstraints.INTEGRATION_ID_TOO_LONG_MSG_KEY, new Object[] {
						EntityConstraints.INTEGRATION_ID_MAX_SIZE, integrationIdSize },
				testLocale);
		assertEquals(expectedMsg, validationMsgs.get(0));
	}

	private User getPopulatedUser() {
		User user = new User();
		populateBaseEntity(user);
		user.setUsername("auser");
		return user;
	}

	private void populateBaseEntity(BaseEntity be) {
		be.setCreated(new Date());
		be.setCreatedBy("SYSTEM");
		be.setIntegrationId(UUID.randomUUID().toString());
		be.setLastUpdate(new Date());
		be.setLastUpdateBy("SYSTEM");
		be.setTechnicalId(UUID.randomUUID().toString());
	}
}
