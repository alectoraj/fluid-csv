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

import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;

/**
 * Attaches a custom {@link CsvColumnMapper} to a property/setter.
 * <p>
 * This would overwrite existing default mapper if there was any
 * </p>
 * 
 * @author Arindam Biswas
 * @since 2.0
 */
@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface CsvDeserializer {

	/**
	 * Specifies the custom column mapper of the given property
	 * 
	 * @return an implementation of {@link CsvColumnMapper} that returns in the
	 *         attached data type
	 */
	Class<? extends CsvColumnMapper<?>> value();

}
