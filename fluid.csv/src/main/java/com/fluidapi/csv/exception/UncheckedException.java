package com.fluidapi.csv.exception;

public class UncheckedException extends RuntimeException {

	/**
	 * configuration: default using {@link RuntimeException}
	 */
	private static final long serialVersionUID = 1L;

	public UncheckedException() {
		super();
	}

	public UncheckedException(String message) {
		super(message);
	}

	public UncheckedException(Throwable cause) {
		super(cause);
	}

	public UncheckedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UncheckedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
