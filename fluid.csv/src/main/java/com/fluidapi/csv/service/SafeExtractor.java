package com.fluidapi.csv.service;

import java.util.function.IntFunction;

/**
 * from a collection or collection type instance,
 * find a subset of collection using given length.
 * 
 * <p>
 * Although maintenance of state is implementation specific,
 * encouraged is to find the length excluding the previously extracted.
 * </p>
 * 
 * @author Arindam Biswas
 * @since 1.0
 *
 * @param <T> {@link Object}
 */
@FunctionalInterface
public interface SafeExtractor<T> extends IntFunction<T> {
	
	T next(int length);
	
	@Override
	default T apply(int value) {
		return next(value);
	}
	
}
