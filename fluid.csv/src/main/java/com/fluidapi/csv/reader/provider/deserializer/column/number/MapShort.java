package com.fluidapi.csv.reader.provider.deserializer.column.number;

public class MapShort extends MapNumber<Short> {

	@Override
	protected Short mapSafe(String column) {
		return Short.valueOf(column);
	}
	
	static {
		support.register(Short.class, (t, u) -> new MapShort());
	}
}
