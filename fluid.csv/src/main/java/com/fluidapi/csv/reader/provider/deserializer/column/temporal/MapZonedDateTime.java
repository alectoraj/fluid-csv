package com.fluidapi.csv.reader.provider.deserializer.column.temporal;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;
import com.fluidapi.csv.reader.provider.bean.AnnotatedInfo;

import lombok.NonNull;

public class MapZonedDateTime extends MapTemporal<ZonedDateTime> {

	public MapZonedDateTime(AnnotatedInfo<?> property) {
		super(property);
	}

	@Override
	protected @NonNull CsvColumnMapper<ZonedDateTime> defaultFormatter() {
		return ZonedDateTime::parse;
	}

	@Override
	protected @NonNull CsvColumnMapper<ZonedDateTime> parseUsing(DateTimeFormatter formatter) {
		return t -> ZonedDateTime.parse(t, formatter);
	}
	
	static {
		support.register(ZonedDateTime.class, (t, origin) -> new MapZonedDateTime(origin));
	}
	
}
