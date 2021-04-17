package com.fluidapi.csv.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * remove the escape characters to convert "Zeno D\'Souza" to "Zeno D'Souza"
 * 
 * @author Arindam Biswas
 * @since 1.0
 */
@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface CsvUnescape {
	
	/**
	 * specify escape character
	 * 
	 * @return escape character, default: \
	 */
	char value() default '\\';
	
}
