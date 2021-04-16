package com.fluidapi.csv.reader.provider.deserializer.column.temporal;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.Temporal;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.fluidapi.csv.annotations.CsvFormat;
import com.fluidapi.csv.reader.provider.bean.AnnotatedInfo;
import com.fluidapi.csv.reader.provider.deserializer.column.MapSafe;
import com.fluidapi.csv.utility.ClassUtils;
import com.fluidapi.csv.utility.MapSupport;

import lombok.NonNull;

public abstract class MapTemporal<T extends Temporal> extends MapSafe<T> {
	
	protected final DateTimeFormatter formatter;

	public MapTemporal(AnnotatedInfo<?> property) {
		formatter = findFormat(property);
	}
	
	private DateTimeFormatter findFormat(AnnotatedInfo<?> property) {
		return Optional.of(property)
				.filter(t -> t.hasAnnotation(CsvFormat.class))
				.map( t -> t.findAnnotation(CsvFormat.class) )
				.map(CsvFormat::value)
				.filter(StringUtils::isNotBlank)
				.map(this::ofPattern)
				.orElse(defaultFormat());
	}
	
	private DateTimeFormatter ofPattern(String format) {
		return new DateTimeFormatterBuilder()
				.parseCaseInsensitive()
				.appendPattern(format)
				.toFormatter();
	}

	protected abstract @NonNull DateTimeFormatter defaultFormat();
	
	public static final MapSupport<MapTemporal<? extends Temporal>> support = new MapSupport<>();
	
	static {
		ClassUtils.load(
				MapLocalDate.class
		);
	}

}
