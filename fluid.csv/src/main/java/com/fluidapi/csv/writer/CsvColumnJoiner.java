package com.fluidapi.csv.writer;

import java.util.function.Function;

/**
 * Takes in a set of columns as {@code String[]} and joins them to form a line in Csv file
 * 
 * @author Arindam Biswas
 * @since 0.2
 */
@FunctionalInterface
public interface CsvColumnJoiner extends Function<String[], String> {

	String join(String[] columns);
	
	@Override
	default String apply(String[] t) {
		return join(t);
	}
	
}
