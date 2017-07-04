package cn.xsintech.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	private String errorCode;
	
	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public BusinessException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

}
