package com.fluidapi.csv.utility;

import java.util.function.IntPredicate;

public interface IntPredicates {
	
	static IntPredicate isZero() {
		return n -> n == 0;
	}

	static IntPredicate isPositive() {
		return n -> n > 0;
	}
	static IntPredicate isZeroOrPositive() {
		return isZero().or(isPositive());
	}
}
