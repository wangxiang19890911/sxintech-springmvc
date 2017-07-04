package cn.xsintech.exception;

public class LockedUserException extends Exception {

	private static final long serialVersionUID = 1L;

	public LockedUserException() {
		super();
	}

	public LockedUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LockedUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public LockedUserException(String message) {
		super(message);
	}

	public LockedUserException(Throwable cause) {
		super(cause);
	}

}
