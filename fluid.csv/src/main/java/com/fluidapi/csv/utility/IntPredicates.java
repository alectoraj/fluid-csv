package com.fluidapi.csv.utility;

import java.util.function.IntPredicate;

/**
 * Provides {@link IntPredicate} implementation shorthands.
 * 
 * @author Arindam Biswas
 * @since 0.1
 */
public interface IntPredicates {

	/**
	 * if a number is zero
	 * 
	 * @return {@link IntPredicate}
	 */
	static IntPredicate isZero() {
		return n -> n == 0;
	}

	/**
	 * if a number is positive, zero is not considered positive
	 * 
	 * @return {@link IntPredicate}
	 */
	static IntPredicate isPositive() {
		return n -> n > 0;
	}

	/**
	 * if a number is zero or greater
	 * 
	 * @return {@link IntPredicate}
	 */
	static IntPredicate isZeroOrPositive() {
		return isZero().or(isPositive());
	}

	/**
	 * if a number is negative, zero is not considered negative
	 * 
	 * @return {@link IntPredicate}
	 */
	static IntPredicate isNegative() {
		return n -> n < 0;
	}
}
