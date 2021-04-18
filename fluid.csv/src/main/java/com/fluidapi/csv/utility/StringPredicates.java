package com.fluidapi.csv.utility;

import static java.util.regex.Pattern.compile;

import java.util.function.Predicate;

/**
 * Provides {@link Predicate Predicate<String>} implementation shorthands.
 * 
 * @author Arindam Biswas
 * @since 0.1
 */
public interface StringPredicates {

	Predicate<String> isGetter = compile("^(get|is)[A-Z]").asPredicate();
	Predicate<String> isSetter = compile("^(set)[A-Z]").asPredicate();
	
}
