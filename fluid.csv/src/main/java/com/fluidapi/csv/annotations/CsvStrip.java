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

import com.fluidapi.csv.bean.Strip;

/**
 * Says the column should be stripped of spaces from around
 * <p>Uses {@link String#strip()}</p>
 * <p>Takes precedence over {@link CsvTrim}</p>
 * 
 * @author Arindam Biswas
 * @since 1.0
 */
@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface CsvStrip {
	
	/**
	 * @return {@link Strip}, default: {@link Strip#AROUND}
	 */
	Strip value() default Strip.AROUND;
	
}
