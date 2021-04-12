package com.fluidapi.csv.exception;

public class CsvFormatException extends UncheckedException {

	/**
	 * configuration: default using {@link UncheckedException}
	 */
	private static final long serialVersionUID = 1L;

	public CsvFormatException() {
		super();
	}

	public CsvFormatException(String message) {
		super(message);
	}

	public CsvFormatException(Throwable cause) {
		super(cause);
	}

	public CsvFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public CsvFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
