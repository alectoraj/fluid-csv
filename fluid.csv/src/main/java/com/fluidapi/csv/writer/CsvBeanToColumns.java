package com.fluidapi.csv.writer;

import java.util.function.Function;

/**
 * Reads a bean and maps it to a set of columns as {@code String[]}
 * 
 * @author Arindam Biswas
 * @since 2.0
 */
@FunctionalInterface
public interface CsvBeanToColumns<T> extends Function<T, String[]> {

	String[] convert(T t);
	
	@Override
	default String[] apply(T t) {
		return convert(t);
	}
	
}
