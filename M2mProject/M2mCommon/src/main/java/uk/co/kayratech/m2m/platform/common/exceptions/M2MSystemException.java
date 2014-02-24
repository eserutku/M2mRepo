package uk.co.kayratech.m2m.platform.common.exceptions;

import org.apache.commons.lang3.RandomStringUtils;

import uk.co.kayratech.m2m.platform.common.i18n.MessageProvider;

public class M2MSystemException extends RuntimeException {

	private static final long serialVersionUID = -39935480267246047L;

	private static final int UNIQ_EXCEPTION_ID_LENGTH = 6;
	private static final String errorPrefix = "SYS ";
	private static final String defaultMessage = errorPrefix + serialVersionUID + ": ";

	private String uniqExceptionId = RandomStringUtils
			.randomAlphanumeric(UNIQ_EXCEPTION_ID_LENGTH).toUpperCase();
	private boolean stackTracePrinted = false;

	private String displayMessage = defaultMessage
			+ MessageProvider.getMessage("system.exception");

	public M2MSystemException() {
		super();
	}

	public M2MSystemException(String errorLogMsg) {
		super(defaultMessage + errorLogMsg);
	}
	
	public M2MSystemException(String errorLogMsg, String displayMessage) {
		super(defaultMessage + errorLogMsg);
		setDisplayMessage(displayMessage);
	}

	public M2MSystemException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getMessage() {
		return (super.getMessage() + " - Error Code: " + uniqExceptionId);
	}

	public String getDisplayMessage() {
		return displayMessage
				+ MessageProvider.getMessage("system.exception.uniq.code",
						new Object[] { uniqExceptionId });
	}
	
	public void setDisplayMessage(String displayMessage) {
		this.displayMessage = displayMessage;
	}

	public boolean isStackTracePrinted() {
		return stackTracePrinted;
	}

	public void setStackTracePrinted(boolean stackTracePrinted) {
		this.stackTracePrinted = stackTracePrinted;
	}
}
