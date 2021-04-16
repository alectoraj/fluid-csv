package com.fluidapi.csv.reader.provider.deserializer.column.temporal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;
import com.fluidapi.csv.reader.provider.bean.AnnotatedInfo;

import lombok.NonNull;

public class MapLocalDateTime extends MapTemporal<LocalDateTime> {

	public MapLocalDateTime(AnnotatedInfo<?> property) {
		super(property);
	}

	@Override
	protected @NonNull CsvColumnMapper<LocalDateTime> defaultFormatter() {
		return LocalDateTime::parse;
	}

	@Override
	protected @NonNull CsvColumnMapper<LocalDateTime> parseUsing(DateTimeFormatter formatter) {
		return t -> LocalDateTime.parse(t, formatter);
	}
	
	static {
		support.register(LocalDateTime.class, (t, origin) -> new MapLocalDateTime(origin));
	}
	
}
