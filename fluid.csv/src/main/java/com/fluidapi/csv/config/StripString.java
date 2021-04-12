package com.fluidapi.csv.config;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Would remove white spaces from around the string using {@link String#strip()}
 * 
 * @author Arindam Biswas
 */
@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface StripString {
	
	/**
	 * @return indication to whether the column should be stripped, suitable for
	 *         fixed length splitting
	 *         <p>
	 *         default {@code true}
	 *         </p>
	 */
	boolean value() default true;
	
}
