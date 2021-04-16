package com.fluidapi.csv.reader.provider.deserializer.column.number;

public class MapLong extends MapNumber<Long> {

	@Override
	protected Long mapSafe(String column) {
		return Long.valueOf(column);
	}
	
	static {
		support.register(Long.class, (t, u) -> new MapLong());
	}
}
