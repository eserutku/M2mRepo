package uk.co.kayratech.m2m.platform.common.exceptions;

import uk.co.kayratech.m2m.platform.common.i18n.MessageProvider;

public class M2MBusinessException extends Exception {

	private static final long serialVersionUID = 3237898999414049071L;

	private static final String LANG_FILE_ERR_CODE_PREFIX = "biz.msg.";

	private String bizErrNo;
	private String displayMessage;

	public M2MBusinessException(String errorLogMsg, String bizErrNo) {
		super(errorLogMsg);
		setBizErrNo(bizErrNo);
		setDisplayMessage(getBizErrNo() + ": "
				+ MessageProvider.getMessage(getLangFileErrCode()));
	}

	public M2MBusinessException(String errorLogMsg, String bizErrNo, Object[] msgParams) {
		super(errorLogMsg);
		setBizErrNo(bizErrNo);
		setDisplayMessage(getBizErrNo() + ": "
				+ MessageProvider.getMessage((getLangFileErrCode()), msgParams));
	}

	public void setBizErrNo(String bizErrNo) {
		this.bizErrNo = bizErrNo;
	}

	public String getBizErrNo() {
		return bizErrNo;
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	public String getLangFileErrCode() {
		return LANG_FILE_ERR_CODE_PREFIX + getBizErrNo();
	}

	public String getDisplayMessage() {
		return displayMessage;
	}

	public void setDisplayMessage(String displayMessage) {
		this.displayMessage = displayMessage;
	}

}