package com.fluidapi.csv.reader.provider.deserializer.column.temporal;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fluidapi.csv.provider.bean.AnnotatedInfo;
import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;

import lombok.NonNull;

public class MapLocalTime extends MapTemporal<LocalTime> {

	public MapLocalTime(AnnotatedInfo<?> property) {
		super(property);
	}

	@Override
	protected @NonNull CsvColumnMapper<LocalTime> defaultFormatter() {
		return LocalTime::parse;
	}

	@Override
	protected @NonNull CsvColumnMapper<LocalTime> parseUsing(DateTimeFormatter formatter) {
		return t -> LocalTime.parse(t, formatter);
	}
	
	static {
		support.register(LocalTime.class, (t, origin) -> new MapLocalTime(origin));
	}
	
}
