package com.fluidapi.csv.reader;

import java.util.function.UnaryOperator;

/**
 * Reads given columns represented as {@code String[]} and decorates each column
 * as specified.
 * <p>
 * Maybe mutable, i.e. may alter the input {@code String[]} to optimize in
 * memory & performance.
 * </p>
 * <p>
 * On the contrary to {@link com.fluidapi.csv.writer.CsvColumnDecorator
 * Writer.CsvColumnDecorator} this decorator reads columns as produced by
 * {@link CsvLineSplitter} <em>from</em> Csv Lines and prepares them to be
 * mapped to Bean using {@link CsvBeanDeserializer}
 * </p>
 * 
 * @author Arindam Biswas
 * @since 0.1
 */
@FunctionalInterface
public interface CsvColumnDecorator extends UnaryOperator<String[]> {

	String[] decorate(String[] columns);
	
	@Override
	default String[] apply(String[] t) {
		return decorate(t);
	}
	
}
