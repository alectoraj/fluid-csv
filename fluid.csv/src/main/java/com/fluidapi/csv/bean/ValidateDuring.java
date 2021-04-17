package com.fluidapi.csv.bean;

import static java.util.Arrays.stream;

import jakarta.validation.Valid;

public enum ValidateDuring {
	
	/**
	 * validate only while reading csv and deserializing to bean
	 */
	DESERIALIZATION,
	
	/**
	 * validate only while serializing from bean and writing to csv
	 */
	SERIALIZATION,
	
	/**
	 * validate during both {@link #SERIALIZATION} and {@link #DESERIALIZATION}
	 * <p>
	 * {@link Valid @Valid} from {@code jakarta.validation} would result in the same
	 * behavior
	 * </p>
	 */
	ALWAYS;
	
	public boolean isOneOf(ValidateDuring...options) {
		return stream(options).anyMatch(this::equals);
	}
	
}
