package com.fluidapi.csv.reader.provider.deserializer.column;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

import org.apache.commons.lang3.StringUtils;

import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;

public class MapSafe<T> implements CsvColumnMapper<T> {

	final CsvColumnMapper<T> safeMapper;
	
	public MapSafe(CsvColumnMapper<T> safeMapper) {
		requireNonNull(safeMapper, "safeMapper");
		this.safeMapper = safeMapper;
	}

	@Override
	public final T map(String column) {
		return ofNullable(column)
				.filter(StringUtils::isNotEmpty)
				.map(safeMapper)
				.orElse(null);
	}

}
