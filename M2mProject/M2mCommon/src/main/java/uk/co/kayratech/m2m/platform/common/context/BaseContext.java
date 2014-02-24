package uk.co.kayratech.m2m.platform.common.context;

import java.util.Locale;

public interface BaseContext {

	public Locale getLocale();

	public void setLocale(Locale locale);

	public String getUsername();

	public void setUsername(String username);

	public String getSessionId();

	public void setSessionId(String sessionId);
}