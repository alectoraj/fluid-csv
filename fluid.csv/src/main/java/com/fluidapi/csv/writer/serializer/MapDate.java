package com.fluidapi.csv.writer.serializer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

import com.fluidapi.csv.annotations.CsvFormat;
import com.fluidapi.csv.annotations.CsvTemporal;
import com.fluidapi.csv.provider.bean.AnnotatedInfo;

public class MapDate extends MapSafe<Date> {

	static final Function<Date, String> dateToString = Date::toString;
	final Function<Date, String> formatter;
	
	public MapDate(AnnotatedInfo<?> property) {
		DateFormat dateFormat = findDateFormat(property);
		formatter = dateFormat != null ? dateFormat::format : dateToString;
	}
	
	@Override
	protected String mapSafe(Date t) {
		return formatter.apply(t);
	}
	
	private DateFormat findDateFormat(AnnotatedInfo<?> property) {
		
		// first priority is to CsvFormat
		if( property.hasAnnotation(CsvFormat.class) ) {
			String format = property.findAnnotation(CsvFormat.class).value();
			return new SimpleDateFormat(format);
			
		// second priority is to CsvTemporal
		} else if( property.hasAnnotation(CsvTemporal.class) ) {
			return switch(property.findAnnotation(CsvTemporal.class).value()) {
				case DATE -> DateFormat.getDateInstance();
				case TIME -> DateFormat.getTimeInstance();
				case TIMESTAMP -> DateFormat.getDateTimeInstance();
			};
		}
		
		// no formatter, choose default option
		return null;
	}
	
	public static boolean supports(Class<?> type) {
		return Date.class.isAssignableFrom(type);
	}

}
