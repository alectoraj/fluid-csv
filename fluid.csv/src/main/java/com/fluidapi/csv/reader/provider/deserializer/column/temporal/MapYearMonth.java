package com.fluidapi.csv.reader.provider.deserializer.column.temporal;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;
import com.fluidapi.csv.reader.provider.bean.AnnotatedInfo;

import lombok.NonNull;

public class MapYearMonth extends MapTemporal<YearMonth> {

	public MapYearMonth(AnnotatedInfo<?> property) {
		super(property);
	}

	@Override
	protected @NonNull CsvColumnMapper<YearMonth> defaultFormatter() {
		return YearMonth::parse;
	}

	@Override
	protected @NonNull CsvColumnMapper<YearMonth> parseUsing(DateTimeFormatter formatter) {
		return t -> YearMonth.parse(t, formatter);
	}
	
	static {
		support.register(YearMonth.class, (t, origin) -> new MapYearMonth(origin));
	}
	
}
