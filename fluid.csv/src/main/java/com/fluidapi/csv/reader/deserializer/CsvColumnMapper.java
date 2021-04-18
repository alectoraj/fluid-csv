package com.fluidapi.csv.reader.deserializer;

import java.util.function.Function;

/**
 * Since the reader does not support complex bean types, One can use this custom
 * column mapper to read a particular part of input and convert it into the
 * complex type.
 * 
 * @author Arindam Biswas
 * @since 0.1
 *
 * @param <T> any bean type
 */
public interface CsvColumnMapper<T> extends Function<String, T> {

	T map(String column);
	
	@Override
	default T apply(String t) {
		return map(t);
	}

}
