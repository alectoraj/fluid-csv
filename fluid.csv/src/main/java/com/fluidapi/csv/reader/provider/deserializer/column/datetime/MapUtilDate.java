package com.fluidapi.csv.reader.provider.deserializer.column.datetime;

import java.util.Date;

import com.fluidapi.csv.provider.bean.AnnotatedInfo;

import lombok.NonNull;

public class MapUtilDate extends MapOldTemporal<Date> {

	public MapUtilDate(AnnotatedInfo<?> property) {
		super(property);
	}

	@Override
	protected @NonNull Date fromUtilDate(Date date) {
		return date;
	}
	
	static {
		support.register(Date.class, (t, property) -> new MapUtilDate(property));
	}

}
