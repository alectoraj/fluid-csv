package com.fluidapi.csv.internal.function;

import static java.util.Objects.requireNonNull;

import java.util.function.BiConsumer;

import com.fluidapi.csv.exception.UncheckedException;

@FunctionalInterface
public interface Setter extends BiConsumer<Object, Object> {
	
	void setUnchecked(Object instance, Object value) throws Exception;
	
	default void set(Object instance, Object value) {
		try {
			setUnchecked(instance, value);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new UncheckedException(e);
		}
	}
	
	@Override
	default void accept(Object instance, Object value) {
		set(instance, value);
	}
	
	/**
	 * @param before a decorative transformation before invoking the setter
	 * @return a setter that first transforms the input and then sets the value
	 */
	default Setter with(Transformation before) {
		requireNonNull(before, "decorator transformation");
		return (instance, value) -> setUnchecked(instance, before.transform(value));
	}
}
