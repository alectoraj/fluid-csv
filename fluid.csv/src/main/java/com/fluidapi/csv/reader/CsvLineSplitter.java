package com.fluidapi.csv.reader;

import java.util.function.Function;

/**
 * Reads a line as {@link String}, and parses it to columns of type {@code String[]}
 * 
 * @author Arindam Biswas
 * @since 0.1
 */
@FunctionalInterface
public interface CsvLineSplitter extends Function<String, String[]> {
	
	String[] split(String line);
	
	@Override
	default String[] apply(String t) {
		return split(t);
	}
	
}
