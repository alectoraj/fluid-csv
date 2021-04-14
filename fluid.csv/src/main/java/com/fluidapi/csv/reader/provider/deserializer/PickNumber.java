package com.fluidapi.csv.reader.provider.deserializer;

import static java.util.Optional.ofNullable;

import org.apache.commons.lang3.StringUtils;

import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;

/**
 * safely picks the string value at given index, parses using the given parser and returns.
 * if the column value is {@code null} or empty to begin with, then {@code null} gets returned.
 * 
 * @author Arindam Biswas
 * @since 1.0
 */
public class PickNumber<T extends Number> extends PickMapped<T> {

	public PickNumber(int index, CsvColumnMapper<T> withMapping) {
		super(index, safeMapper(withMapping));
	}

	private static <T extends Number> CsvColumnMapper<T> safeMapper(CsvColumnMapper<T> withMapping) {
		return column -> ofNullable(column)
				.filter(StringUtils::isNotEmpty)
				.map(withMapping)
				.orElse(null);
	}

}
