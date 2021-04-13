package com.fluidapi.csv.config;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface CsvFormat {
	
	/**
	 * Supported formats should be mapped using existing providers as below -
	 * <ul>
	 * <li>{@code java.time} - {@link DateTimeFormatter}</li>
	 * <li>{@code java.util.Date/Calendar} - {@link SimpleDateFormat}</li>
	 * <li>{@code java.sql.Date/Timestamp} - {@link SimpleDateFormat}</li>
	 * </ul>
	 * @return format of the column in string, with respect to target data type
	 */
	String value();
	
}
