package com.fluidapi.csv.internal.validation;

import java.util.function.Function;
import java.util.function.Supplier;

public interface Requires {

	static void requireTrue(boolean flag, String message) {
		requireTrue(flag, lazy(message), IllegalArgumentException::new);
	}
	static void requireTrue(boolean flag, Supplier<String> message, Function<String, RuntimeException> exception) {
		if( !flag ) {
			throw exception.apply(message.get());
		}
	}
	
	private static <T> Supplier<T> lazy(T t) {
		return () -> t;
	}
	
}
