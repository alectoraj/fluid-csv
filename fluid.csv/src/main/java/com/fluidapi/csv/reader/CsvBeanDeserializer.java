package com.fluidapi.csv.reader;

import java.util.function.Function;

/**
 * Reads a given {@code String[]} set of columns, and maps them to a bean as requested
 * 
 * @author Arindam Biswas
 * @since 0.1
 */
@FunctionalInterface
public interface CsvBeanDeserializer<T> extends Function<String[], T> {
	
	T convert(String[] columns);
	
	@Override
	default T apply(String[] t) {
		return convert(t);
	}
	
}
