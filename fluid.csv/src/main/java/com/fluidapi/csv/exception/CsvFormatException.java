package com.fluidapi.csv.exception;

public class CsvFormatException extends CsvException {

	/**
	 * basic formation, specialized subclass of {@link RuntimeException}
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
