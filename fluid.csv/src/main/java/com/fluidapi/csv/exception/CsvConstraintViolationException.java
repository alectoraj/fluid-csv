package com.fluidapi.csv.exception;

public class CsvConstraintViolationException extends CsvException {

	/**
	 * basic formation, specialized subclass of {@link RuntimeException}
	 */
	private static final long serialVersionUID = 1L;

	public CsvConstraintViolationException() {
		super();
	}

	public CsvConstraintViolationException(String message) {
		super(message);
	}

	public CsvConstraintViolationException(Throwable cause) {
		super(cause);
	}

	public CsvConstraintViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public CsvConstraintViolationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
