package com.fluidapi.csv.service;

import java.util.function.IntFunction;

@FunctionalInterface
public interface SafeExtractor<T> extends IntFunction<T> {
	
	T next(int length);
	
	@Override
	default T apply(int value) {
		return next(value);
	}
	
}
