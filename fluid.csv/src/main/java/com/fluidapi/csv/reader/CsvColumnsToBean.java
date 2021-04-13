package com.fluidapi.csv.reader;

import java.util.function.Function;

/**
 * Reads a given {@code String[]} set of columns, and maps them to a bean as requested
 * 
 * @author Arindam Biswas
 * @since 1.0
 */
@FunctionalInterface
public interface CsvColumnsToBean<T> extends Function<String[], T> {
	
	T convert(String[] columns);
	
	@Override
	default T apply(String[] t) {
		return convert(t);
	}
	
}
