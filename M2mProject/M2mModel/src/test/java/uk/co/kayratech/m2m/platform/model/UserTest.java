package uk.co.kayratech.m2m.platform.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.kayratech.m2m.platform.common.context.InheritableThreadLocalContext;
import uk.co.kayratech.m2m.platform.common.i18n.MessageProvider;

public class UserTest extends M2mModelBaseTest {

	private static final int INTEGRATION_ID_MAX_SIZE = 100;
	
	private static final Locale testLocale = Locale.ENGLISH;
	
	@BeforeClass
	public static void beforeClass() {
		InheritableThreadLocalContext.instance.get().setLocale(testLocale);
	}

	@Test
	public void testObjectWithNullTechnicalIdFailsEquals() {
		User user = new User();
		user.setTechnicalId(UUID.randomUUID().toString());
		user.setIntegrationId(UUID.randomUUID().toString());
		
		User anotherUser = new User();
		anotherUser.setTechnicalId(user.getTechnicalId());
		anotherUser.setIntegrationId(user.getIntegrationId());
		user.setTechnicalId(null);

		assertFalse(user.equals(anotherUser));
	}

	@Test
	public void testIfOtherObjectHasNullTechnicalIdEqualsFails() {
		User user = new User();
		user.setTechnicalId(UUID.randomUUID().toString());
		user.setIntegrationId(UUID.randomUUID().toString());
		
		User anotherUser = new User();
		anotherUser.setTechnicalId(user.getTechnicalId());
		anotherUser.setIntegrationId(user.getIntegrationId());
		anotherUser.setTechnicalId(null);

		assertFalse(user.equals(anotherUser));
	}

	@Test
	public void testIntegrationIdTooLongMessageWithMaxValueCanBeConstructed() {
		StringBuffer longIntegrationId = new StringBuffer();
		while (longIntegrationId.length() <= INTEGRATION_ID_MAX_SIZE) {
			longIntegrationId.append(UUID.randomUUID().toString());
		}
		int integrationIdSize = longIntegrationId.length();
		User user = new User();
		user.setTechnicalId(UUID.randomUUID().toString());
		user.setIntegrationId(longIntegrationId.toString());

		List<String> validationMsgs = BaseEntity.validate(user);

		assertTrue(validationMsgs.size() > 1);
		
		String expectedMsg = MessageProvider.getMessage(
				"integrationid.too.long", new Object[] {
						INTEGRATION_ID_MAX_SIZE, integrationIdSize }, testLocale);
		for (String validationMsg : validationMsgs)
		{
			if (validationMsg.equals(expectedMsg)) return;
		}

		fail("None of the validation messages matched expected message: " + expectedMsg);
	}
}
