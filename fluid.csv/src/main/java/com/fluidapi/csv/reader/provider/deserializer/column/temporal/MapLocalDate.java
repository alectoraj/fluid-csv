package com.fluidapi.csv.reader.provider.deserializer.column.temporal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fluidapi.csv.reader.provider.bean.AnnotatedInfo;

import lombok.NonNull;

public class MapLocalDate extends MapTemporal<LocalDate> {

	public MapLocalDate(AnnotatedInfo<?> property) {
		super(property);
	}

	@Override
	protected LocalDate mapSafe(String column) {
		return LocalDate.parse(column, formatter);
	}

	@Override
	protected @NonNull DateTimeFormatter defaultFormat() {
		return DateTimeFormatter.ISO_LOCAL_DATE;
	}
	
	static {
		support.register(LocalDate.class, (t, origin) -> new MapLocalDate(origin));
	}
	
}
