package com.fluidapi.csv.reader.provider.deserializer.column.temporal;

import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;

import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;
import com.fluidapi.csv.reader.provider.bean.AnnotatedInfo;

import lombok.NonNull;

public class MapOffsetTime extends MapTemporal<OffsetTime> {

	public MapOffsetTime(AnnotatedInfo<?> property) {
		super(property);
	}

	@Override
	protected @NonNull CsvColumnMapper<OffsetTime> defaultFormatter() {
		return OffsetTime::parse;
	}

	@Override
	protected @NonNull CsvColumnMapper<OffsetTime> parseUsing(DateTimeFormatter formatter) {
		return t -> OffsetTime.parse(t, formatter);
	}
	
	static {
		support.register(OffsetTime.class, (t, origin) -> new MapOffsetTime(origin));
	}
	
}
