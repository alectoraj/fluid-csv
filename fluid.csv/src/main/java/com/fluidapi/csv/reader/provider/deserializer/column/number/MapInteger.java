package com.fluidapi.csv.reader.provider.deserializer.column.number;

public class MapInteger extends MapNumber<Integer> {

	@Override
	protected Integer mapSafe(String column) {
		return Integer.valueOf(column);
	}
	
	static {
		support.register(Integer.class, (t, u) -> new MapInteger());
	}
}
