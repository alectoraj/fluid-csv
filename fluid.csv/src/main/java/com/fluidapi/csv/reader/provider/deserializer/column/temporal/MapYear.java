package com.fluidapi.csv.reader.provider.deserializer.column.temporal;

import java.time.Year;
import java.time.format.DateTimeFormatter;

import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;
import com.fluidapi.csv.reader.provider.bean.AnnotatedInfo;

import lombok.NonNull;

public class MapYear extends MapTemporal<Year> {

	public MapYear(AnnotatedInfo<?> property) {
		super(property);
	}

	@Override
	protected @NonNull CsvColumnMapper<Year> defaultFormatter() {
		return Year::parse;
	}

	@Override
	protected @NonNull CsvColumnMapper<Year> parseUsing(DateTimeFormatter formatter) {
		return t -> Year.parse(t, formatter);
	}
	
	static {
		support.register(Year.class, (t, origin) -> new MapYear(origin));
	}
	
}
