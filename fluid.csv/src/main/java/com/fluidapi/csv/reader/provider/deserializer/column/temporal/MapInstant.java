package com.fluidapi.csv.reader.provider.deserializer.column.temporal;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;
import com.fluidapi.csv.reader.provider.bean.AnnotatedInfo;

import lombok.NonNull;

public class MapInstant extends MapTemporal<Instant> {

	public MapInstant(AnnotatedInfo<?> property) {
		super(property);
	}

	@Override
	protected @NonNull CsvColumnMapper<Instant> defaultFormatter() {
		return Instant::parse;
	}

	@Override
	protected CsvColumnMapper<Instant> parseUsing(DateTimeFormatter formatter) {
		return null;
	}
	
	static {
		support.register(Instant.class, (t, origin) -> new MapInstant(origin));
	}
	
}
