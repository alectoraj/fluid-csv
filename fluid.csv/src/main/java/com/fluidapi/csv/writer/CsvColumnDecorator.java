package com.fluidapi.csv.writer;

import java.util.function.UnaryOperator;

/**
 * Reads given columns represented as {@code String[]} and decorates each column
 * as specified.
 * <p>
 * Maybe mutable, i.e. may alter the input {@code String[]} to optimize in
 * memory & performance.
 * </p>
 * <p>
 * On the contrary to {@link com.fluidapi.csv.reader.CsvColumnDecorator
 * Reader.CsvColumnDecorator} this decorator reads columns as produced by
 * {@link CsvBeanSerializer} and prepares them before being converted to Csv
 * Lines.
 * </p>
 * 
 * @author Arindam Biswas
 * @since 0.2
 */
@FunctionalInterface
public interface CsvColumnDecorator extends UnaryOperator<String[]> {

	String[] decorate(String[] columns);
	
	@Override
	default String[] apply(String[] t) {
		return decorate(t);
	}
	
}