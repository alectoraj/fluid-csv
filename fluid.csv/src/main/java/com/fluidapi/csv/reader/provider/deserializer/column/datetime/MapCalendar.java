package com.fluidapi.csv.reader.provider.deserializer.column.datetime;

import java.util.Calendar;
import java.util.Date;

import com.fluidapi.csv.reader.provider.bean.AnnotatedInfo;

import lombok.NonNull;

public class MapCalendar extends MapOldTemporal<Calendar> {

	public MapCalendar(AnnotatedInfo<?> property) {
		super(property);
	}

	@Override
	protected @NonNull Calendar fromUtilDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime()); // setTime(Date) just does this, but with another check
		return calendar;
	}
	
	static {
		support.register(Calendar.class, (t, property) -> new MapCalendar(property));
	}

}
