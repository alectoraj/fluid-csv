package com.fluidapi.csv.writer.serializer;

import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.function.Function;

import com.fluidapi.csv.annotations.CsvFormat;
import com.fluidapi.csv.provider.bean.AnnotatedInfo;

public class MapTemporal extends MapSafe<Temporal> {
	
	final Function<Temporal, String> formatter;
	
	public MapTemporal(AnnotatedInfo<?> property) {
		if( property.hasAnnotation(CsvFormat.class) ) {
			formatter = DateTimeFormatter.ofPattern(property.findAnnotation(CsvFormat.class).value())::format;
		} else {
			formatter = Object::toString;
		}
	}

	@Override
	public String apply(Temporal t) {
		return formatter.apply(t);
	}
	
	public static boolean supports(Class<?> type) {
		return Temporal.class.isAssignableFrom(type);
	}

}
