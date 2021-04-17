package com.fluidapi.csv.reader.provider.deserializer.column.datetime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.fluidapi.csv.annotations.CsvFormat;
import com.fluidapi.csv.annotations.CsvTemporal;
import com.fluidapi.csv.exception.CsvFormatException;
import com.fluidapi.csv.provider.bean.AnnotatedInfo;
import com.fluidapi.csv.reader.provider.deserializer.column.MapSafe;
import com.fluidapi.csv.utility.ClassUtils;
import com.fluidapi.csv.utility.MapSupport;

import lombok.NonNull;

/**
 * Focused on to {@code java.util} temporal classes
 * 
 * @author Arindam Biswas
 * @since 1.0
 * @param <T> {@link Date} or its {@code java.sql} subclasses, or {@link Calendar}
 */
public abstract class MapOldTemporal<T> extends MapSafe<T> {
	
	protected final DateFormat dateFormat;

	public MapOldTemporal(AnnotatedInfo<?> property) {
		dateFormat = findFormat(property);
	}
	
	@Override
	protected T mapSafe(String column) {
		try {
			return fromUtilDate(dateFormat.parse(column));
		} catch (ParseException e) {
			throw new CsvFormatException("error translating date string - " + column, e);
		}
	}
	
	private DateFormat findFormat(AnnotatedInfo<?> property) {
		return Optional.of(property)
				.filter(t -> t.hasAnnotation(CsvFormat.class))
				.map( t -> t.findAnnotation(CsvFormat.class) )
				.map(CsvFormat::value)
				.filter(StringUtils::isNotBlank)
				.map(this::ofPattern)
				.orElse(defaultFormat(property));
	}
	
	private DateFormat ofPattern(String format) {
		return new SimpleDateFormat(format);
	}

	protected DateFormat defaultFormat(AnnotatedInfo<?> property) {
		if( property.hasAnnotation(CsvTemporal.class) ) {
			return switch (property.findAnnotation(CsvTemporal.class).value()) {
				case DATE -> DateFormat.getDateInstance();
				case TIME -> DateFormat.getTimeInstance();
				case TIMESTAMP -> DateFormat.getDateTimeInstance();
				// the value just cannot be null, no default needed
			};
		}
		
		return DateFormat.getDateTimeInstance();
	}
	
	protected abstract @NonNull T fromUtilDate(Date date);
	
	public static final MapSupport<MapOldTemporal<?>> support = new MapSupport<>();
	
	static {
		ClassUtils.load(
				MapUtilDate.class,
				MapSqlDate.class,
				MapSqlTimestamp.class,
				MapCalendar.class
		);
	}

}
