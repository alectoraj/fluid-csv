/**
 * 
 */
package com.fluidapi.csv.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Calendar;
import java.util.Date;

import com.fluidapi.csv.bean.TemporalType;

/**
 * For {@link Date}, its {@code java.sql} sub-classes and {@link Calendar}, if
 * there's no {@link CsvFormat @CsvFormat} specified, one need to specify the
 * temporal type.
 * 
 * <p>
 * default: {@link TemporalType#TIMESTAMP}
 * </p>
 * 
 * <p>
 * Not necessary on {@code java.time} classes since they represent the types
 * directly
 * </p>
 * 
 * @author Arindam Biswas
 * @since 0.1
 */
@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface CsvTemporal {

	/**
	 * Specifies the temporal type of the column
	 * 
	 * @return {@link TemporalType} of the field
	 */
	TemporalType value() default TemporalType.TIMESTAMP;

}
