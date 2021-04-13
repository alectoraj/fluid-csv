package com.fluidapi.csv.validaton;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * defines quick validation methods.
 * 
 * @author Arindam Biswas
 * @since 1.0
 */
public interface FailCheck {

	/**
	 * if state if {@code true} throws exception with given message
	 * 
	 * @param state   if {@code true}, fail
	 * @param message the message to include in exception
	 * @throws IllegalArgumentException by default
	 */
	static void failIf(boolean state, String message) {
		failIf(state, () -> message);
	}

	/**
	 * if state if {@code true} throws exception with given message
	 * 
	 * @param state   if {@code true}, fail
	 * @param message the lazily constructed message to include in exception
	 * @throws IllegalArgumentException by default
	 */
	static void failIf(boolean state, Supplier<String> message) {
		failIf(state, message, IllegalArgumentException::new);
	}
	
	/**
	 * if state if {@code true} throws exception of given type with given message
	 * 
	 * @param state     if {@code true}, fail
	 * @param message   the message to include in exception
	 * @param exception the exception constructor that takes a string for message as
	 *                  parameter
	 * @throws a runtime exception of constructed type
	 */
	static void failIf(boolean state, String message, Function<String, RuntimeException> exception) {
		failIf(state, () -> message, exception);
	}
	
	/**
	 * if state if {@code true} throws exception of given type with given message
	 * 
	 * @param state     if {@code true}, fail
	 * @param message   the lazily constructed message to include in exception
	 * @param exception the exception constructor that takes a string for message as
	 *                  parameter
	 * @throws a runtime exception of constructed type
	 */
	static void failIf(boolean state, Supplier<String> message, Function<String, RuntimeException> exception) {
		if( state ) {
			throw exception.apply(message.get());
		}
	}
	
}
