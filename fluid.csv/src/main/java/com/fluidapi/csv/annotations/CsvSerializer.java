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

import com.fluidapi.csv.writer.serializer.CsvBeanMapper;

/**
 * Attaches a custom {@link CsvBeanMapper} to a property/getter.
 * <p>
 * This would overwrite existing default mapper if there was any
 * </p>
 * 
 * @author Arindam Biswas
 * @since 0.2
 */
@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface CsvSerializer {

	/**
	 * Specifies the custom bean mapper of the given property
	 * 
	 * @return an implementation of {@link CsvBeanMapper} that takes in the attached
	 *         data type
	 */
	Class<? extends CsvBeanMapper<?>> value();

}
