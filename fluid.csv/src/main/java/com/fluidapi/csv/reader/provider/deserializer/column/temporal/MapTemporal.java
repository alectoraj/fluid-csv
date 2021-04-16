package com.fluidapi.csv.reader.provider.deserializer.column.temporal;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.Temporal;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.fluidapi.csv.annotations.CsvFormat;
import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;
import com.fluidapi.csv.reader.provider.bean.AnnotatedInfo;
import com.fluidapi.csv.reader.provider.deserializer.column.MapSafe;
import com.fluidapi.csv.utility.ClassUtils;
import com.fluidapi.csv.utility.MapSupport;

import lombok.NonNull;

public abstract class MapTemporal<T extends Temporal> extends MapSafe<T> {
	
	protected final CsvColumnMapper<T> formatter;

	public MapTemporal(AnnotatedInfo<?> property) {
		formatter = findFormat(property);
	}
	
	@Override
	protected T mapSafe(String column) {
		return formatter.apply(column);
	}
	
	private CsvColumnMapper<T> findFormat(AnnotatedInfo<?> property) {
		return Optional.of(property)
				.filter(t -> t.hasAnnotation(CsvFormat.class))
				.map( t -> t.findAnnotation(CsvFormat.class) )
				.map(CsvFormat::value)
				.filter(StringUtils::isNotBlank)
				.map(this::ofPattern)
				.map(this::parseUsing)
				.orElse(defaultFormatter());
	}
	
	private DateTimeFormatter ofPattern(String format) {
		return new DateTimeFormatterBuilder()
				.parseCaseInsensitive()
				.appendPattern(format)
				.toFormatter();
	}

	protected abstract @NonNull CsvColumnMapper<T> defaultFormatter();
	protected abstract CsvColumnMapper<T> parseUsing(DateTimeFormatter formatter);
	
	public static final MapSupport<MapTemporal<? extends Temporal>> support = new MapSupport<>();
	
	static {
		ClassUtils.load(
				MapInstant.class,
				MapLocalDate.class,
				MapLocalTime.class,
				MapLocalDateTime.class,
				MapOffsetTime.class,
				MapOffsetDateTime.class,
				MapZonedDateTime.class,
				MapYearMonth.class,
				MapYear.class
		);
	}

}
