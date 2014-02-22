package uk.co.kayratech.m2m.platform.common.integration;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;

import uk.co.kayratech.m2m.platform.common.i18n.MessageProvider;

public class I18nTests {

	private static final String TEST_MSG_KEY = "hello.message";
	private static final String TEST_MSG_DEFAULT = "hello";
	private static final String TEST_MSG_TR = "selam";
	@Test
	public void testMessageInRightLocaleIsReceived() {
		Locale dummyLocale = new Locale("aa", "", "");
		String messageEn = MessageProvider.getMessage(TEST_MSG_KEY, null, dummyLocale);
		assertEquals(TEST_MSG_DEFAULT, messageEn);
		
		String messageTr = 	MessageProvider.getMessage(TEST_MSG_KEY, null, MessageProvider.LOCALE_TR);
		assertEquals(TEST_MSG_TR, messageTr);
	}
}
