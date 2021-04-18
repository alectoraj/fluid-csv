package com.fluidapi.csv.reader.provider.deserializer.column.temporal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fluidapi.csv.provider.bean.AnnotatedInfo;
import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;

import lombok.NonNull;

public class MapLocalDate extends MapTemporal<LocalDate> {

	public MapLocalDate(AnnotatedInfo<?> property) {
		super(property);
	}

	@Override
	protected @NonNull CsvColumnMapper<LocalDate> defaultFormatter() {
		return LocalDate::parse;
	}

	@Override
	protected @NonNull CsvColumnMapper<LocalDate> parseUsing(DateTimeFormatter formatter) {
		return t -> LocalDate.parse(t, formatter);
	}
	
	static {
		support.register(LocalDate.class, (t, origin) -> new MapLocalDate(origin));
	}
	
}
