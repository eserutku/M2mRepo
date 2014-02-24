package uk.co.kayratech.m2m.platform.common.i18n;

import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import uk.co.kayratech.m2m.platform.common.context.InheritableThreadLocalContext;

public class MessageProvider {
	public static final Locale LOCALE_TR = new Locale("tr", "", "");

	private static ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"/META-INF/spring/m2mCommonAppContext.xml");

	public static String getMessage(String messageKey) {
		return getMessage(messageKey, null);
	}

	public static String getMessage(String messageKey, Object[] params) {
		// TODO: Check InheritableThreadLocalContext does not have null locale at API entry
		Locale locale = InheritableThreadLocalContext.instance.get().getLocale();
		return getMessage(messageKey, params, locale);
	}
	
	public static String getMessage(String messageKey, Object[] params, Locale locale) {
		return ctx.getMessage(messageKey, params, locale);
	}
}
