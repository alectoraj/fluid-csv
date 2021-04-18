package com.fluidapi.csv.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.fluidapi.csv.bean.LetterCase;

/**
 * Convert column to lower case before deserialization or after serialization
 * 
 * @author Arindam Biswas
 * @since 0.1
 */
@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface CsvLetterCase {
	
	/**
	 * specify which transformation to be done
	 * 
	 * @return {@link LetterCase}
	 */
	LetterCase value();
	
}
