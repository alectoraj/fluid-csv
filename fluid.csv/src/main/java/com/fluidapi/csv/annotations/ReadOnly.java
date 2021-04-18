package com.fluidapi.csv.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Since the api allows tagging multiple fields & setter-type methods with
 * {@link CsvColumn @CsvColumn} and that it passes values to all of them,
 * <em>and</em> since we cannot write multiple values to the same column index,
 * one need to ensure only one writable column exists.
 * <p>
 * In order to do that, the api resorts to excluding non-writable columns.
 * </p>
 * <p>
 * To declare a column as non-writable, i.e. to exclude it from being written to
 * csv file, mark a field or a getter-type method having
 * {@link CsvColumn @CsvColumn} with {@link ReadOnly @ReadOnly} annotation.
 * </p>
 * 
 * @author Arindam Biswas
 * @since 1.2
 */
@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface ReadOnly {
	
	// just marking is enough
	
}
