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
 * To be used with parsable columns, to specify the string based pattern.
 * <p>
 * Since they would use existing parsers & formatters, here are the formats one
 * would follow for different data types
 * </p>
 * <table>
 * <style scoped>
 * table, th, td { border: 1px #999999 solid; }
 * th, td { padding: 2mm; text-align: left; vertical-align: bottom; }
 * </style>
 * <tr>
 * <th>Type</th>
 * <th>Parser/Formatter Class</th>
 * </tr>
 * 
 * <tr>
 * <td>{@code java.time}</td>
 * <td>{@link java.time.DateTimeFormatter}</td>
 * </tr>
 * 
 * <tr>
 * <td>
 * {@link java.util.Calendar}<br/>
 * {@link java.util.Date}<br/>
 * {@link java.sql.Date}<br/>
 * {@link java.sql.Timestamp}
 * </td>
 * <td>{@link java.text.SimpleDateFormat}</td>
 * </tr>
 * 
 * <tr>
 * <td>{@code decimal numbers}</td>
 * <td>{@link java.text.DecimalFormat}</td>
 * </tr>
 * </table>
 * 
 * <p>
 * This annotation will be ignored if the type is not a supported type that can
 * be parsed/formatted, or if there's any custom serializer/deserializer
 * </p>
 * 
 * @author Arindam Biswas
 * @since 0.1
 */
@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface CsvFormat {

	/**
	 * Specifies the format it should be using for auto-parsing/formatting of the respective field
	 * 
	 * @return string based pattern as specified above
	 */
	String value();

}
