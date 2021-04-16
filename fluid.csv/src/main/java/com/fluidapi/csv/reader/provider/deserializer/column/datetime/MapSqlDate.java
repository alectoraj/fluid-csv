package com.fluidapi.csv.reader.provider.deserializer.column.datetime;

import java.sql.Date;

import com.fluidapi.csv.reader.provider.bean.AnnotatedInfo;

import lombok.NonNull;

public class MapSqlDate extends MapOldTemporal<Date> {

	public MapSqlDate(AnnotatedInfo<?> property) {
		super(property);
	}

	@Override
	protected @NonNull Date fromUtilDate(java.util.Date date) {
		return new Date(date.getTime());
	}
	
	static {
		support.register(Date.class, (t, property) -> new MapSqlDate(property));
	}

}
