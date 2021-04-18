package com.fluidapi.csv.reader.provider.deserializer.column;

import static java.util.Optional.ofNullable;

import org.apache.commons.lang3.StringUtils;

import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;

public abstract class MapSafe<T> implements CsvColumnMapper<T> {

	@Override
	public final T map(String column) {
		return ofNullable(column)
				.filter(StringUtils::isNotEmpty)
				.map(this::mapSafe)
				.orElse(defaultIfEmpty(column));
	}
	
	protected T defaultIfEmpty(String column) {
		return null;
	}
	
	protected abstract T mapSafe(String column);

}
