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

import com.fluidapi.csv.bean.TemporalTypes;

/**
 * For {@link Date}, its {@code java.sql} sub-classes and {@link Calendar}, if
 * there's no {@link CsvFormat @CsvFormat} specified, one need to specify the
 * temporal type.
 * 
 * <p>
 * default: {@link TemporalTypes#TIMESTAMP}
 * </p>
 * 
 * <p>
 * Not necessary on {@code java.time} classes since they represent the types
 * directly
 * </p>
 * 
 * @author Arindam Biswas
 * @since 1.0
 */
@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface CsvTemporal {

	/**
	 * Specifies the temporal type of the column
	 * 
	 * @return {@link TemporalTypes} of the field
	 */
	TemporalTypes value();

}
