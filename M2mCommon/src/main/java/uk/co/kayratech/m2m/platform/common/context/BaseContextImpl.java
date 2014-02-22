package uk.co.kayratech.m2m.platform.common.context;

import java.util.Locale;

public class BaseContextImpl implements BaseContext {
	private Locale locale;
	private String username;
	private String sessionId;
	
	@Override
	public Locale getLocale() {
		return locale;
	}

	@Override
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getSessionId() {
		return sessionId;
	}

	@Override
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
