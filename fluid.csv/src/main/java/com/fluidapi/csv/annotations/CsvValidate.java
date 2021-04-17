/**
 * 
 */
package com.fluidapi.csv.annotations;

import static com.fluidapi.csv.bean.ValidateDuring.ALWAYS;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.fluidapi.csv.bean.ValidateDuring;

import jakarta.validation.Valid;

/**
 * Put on a class to enable validation.
 * <p>
 * Alternatively one may use {@link Valid @Valid} from
 * {@code jakarta.validation} to enable validation during both reading and
 * writing
 * </p>
 * 
 * @author Arindam Biswas
 * @since 1.0
 */
@Documented
@Retention(RUNTIME)
@Target({ TYPE })
public @interface CsvValidate {

	/**
	 * Specifies the phase while validation should be activated
	 * 
	 * @return {@link ValidateDuring} instance, default: {@link ValidateDuring#ALWAYS}
	 */
	ValidateDuring value() default ALWAYS;

}
