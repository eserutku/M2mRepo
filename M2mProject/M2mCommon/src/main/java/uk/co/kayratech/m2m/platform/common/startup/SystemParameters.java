package uk.co.kayratech.m2m.platform.common.startup;

public class SystemParameters {
	private static String masterKey;

	protected static String getMasterKey() {
		return masterKey;
	}

	protected static synchronized void setMasterKey(String masterKey) {
		SystemParameters.masterKey = masterKey;
	}
}
