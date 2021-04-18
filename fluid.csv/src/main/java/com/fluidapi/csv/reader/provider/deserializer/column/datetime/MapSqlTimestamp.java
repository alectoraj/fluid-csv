package com.fluidapi.csv.reader.provider.deserializer.column.datetime;

import java.sql.Timestamp;
import java.util.Date;

import com.fluidapi.csv.provider.bean.AnnotatedInfo;

import lombok.NonNull;

public class MapSqlTimestamp extends MapOldTemporal<Timestamp> {

	public MapSqlTimestamp(AnnotatedInfo<?> property) {
		super(property);
	}

	@Override
	protected @NonNull Timestamp fromUtilDate(Date date) {
		return new Timestamp(date.getTime());
	}
	
	static {
		support.register(Timestamp.class, (t, property) -> new MapSqlTimestamp(property));
	}

}
