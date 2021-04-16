package com.fluidapi.csv.reader.provider.deserializer.column.temporal;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;
import com.fluidapi.csv.reader.provider.bean.AnnotatedInfo;

import lombok.NonNull;

public class MapOffsetDateTime extends MapTemporal<OffsetDateTime> {

	public MapOffsetDateTime(AnnotatedInfo<?> property) {
		super(property);
	}

	@Override
	protected @NonNull CsvColumnMapper<OffsetDateTime> defaultFormatter() {
		return OffsetDateTime::parse;
	}

	@Override
	protected @NonNull CsvColumnMapper<OffsetDateTime> parseUsing(DateTimeFormatter formatter) {
		return t -> OffsetDateTime.parse(t, formatter);
	}
	
	static {
		support.register(OffsetDateTime.class, (t, origin) -> new MapOffsetDateTime(origin));
	}
	
}
