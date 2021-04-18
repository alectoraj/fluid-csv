package com.fluidapi.csv.writer.serializer;

import java.util.Calendar;
import java.util.Date;
import java.util.function.Function;

import com.fluidapi.csv.provider.bean.AnnotatedInfo;

public class MapCalendar extends MapSafe<Calendar> {
	
	final Function<Calendar, String> formatter;
	
	public MapCalendar(AnnotatedInfo<?> property) {
		MapDate dateMap = new MapDate(property);
		if( dateMap.formatter == MapDate.dateToString ) {
			Function<Calendar, Date> toDate = Calendar::getTime;
			formatter = toDate.andThen(dateMap.formatter);
		} else {
			formatter = Calendar::toString;
		}
	}
	
	@Override
	protected String mapSafe(Calendar t) {
		return formatter.apply(t);
	}
	
	public static boolean supports(Class<?> type) {
		return Calendar.class.isAssignableFrom(type);
	}
	
}
