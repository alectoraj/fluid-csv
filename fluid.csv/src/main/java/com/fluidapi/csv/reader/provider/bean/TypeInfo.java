package com.fluidapi.csv.reader.provider.bean;

import static java.util.Arrays.stream;

import java.lang.reflect.AnnotatedElement;

// although has a single abstract method
// it is not intended to be used as a functional interface
public interface TypeInfo<T extends AnnotatedElement> {
	
	Class<?> getType();

	default boolean isOfType(Class<?> type) {
		return getType().equals(type);
	}
	default boolean isOfType(Class<?>...types) {
		return stream(types).anyMatch(getType()::equals);
	}
	
}
