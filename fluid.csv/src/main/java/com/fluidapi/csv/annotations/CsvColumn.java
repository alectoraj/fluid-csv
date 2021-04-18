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

/**
 * Specifies a property or setter/getter as a column in Csv file, along with
 * specifying which column does it represent
 * 
 * @author Arindam Biswas
 * @since 0.1
 */
@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface CsvColumn {

	/**
	 * Specifies the index of the column this property represents to
	 * 
	 * @return 0-based index of the column in Csv
	 */
	int value();

}
