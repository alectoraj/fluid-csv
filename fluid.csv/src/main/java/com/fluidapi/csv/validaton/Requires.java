package com.fluidapi.csv.validaton;

import java.util.function.Function;
import java.util.function.Supplier;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Requires {

	public static void requireTrue(boolean state, String message) {
		requireTrue(state, () -> message);
	}
	public static void requireTrue(boolean state, Supplier<String> message) {
		requireTrue(state, message, IllegalArgumentException::new);
	}
	public static void requireTrue(boolean state, Supplier<String> message, Function<String, RuntimeException> exception) {
		failIf( !state, message, exception );
	}

	public static void failIf(boolean state, String message) {
		failIf(state, () -> message);
	}
	public static void failIf(boolean state, Supplier<String> message) {
		failIf(state, message, IllegalArgumentException::new);
	}
	public static void failIf(boolean state, String message, Function<String, RuntimeException> exception) {
		failIf(state, () -> message, exception);
	}
	public static void failIf(boolean state, Supplier<String> message, Function<String, RuntimeException> exception) {
		if( state ) {
			throw exception.apply(message.get());
		}
	}
	
}
