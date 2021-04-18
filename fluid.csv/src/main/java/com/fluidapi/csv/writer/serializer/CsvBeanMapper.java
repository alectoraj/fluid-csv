package com.fluidapi.csv.writer.serializer;

import java.util.function.Function;

/**
 * Since the writer does not support complex types, one may use this bean mapper
 * to take in a complex type property to provide the equivalent column
 * {@link String} value
 * 
 * @author Arindam Biswas
 * @since 0.2
 *
 * @param <T> any bean type
 */
@FunctionalInterface
public interface CsvBeanMapper<T> extends Function<T, String> {
	// abstract method: apply
}
