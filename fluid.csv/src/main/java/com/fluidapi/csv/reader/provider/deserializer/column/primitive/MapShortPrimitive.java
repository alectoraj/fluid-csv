package com.fluidapi.csv.reader.provider.deserializer.column.primitive;

public class MapShortPrimitive extends MapPrimitive<Short> {

	@Override
	protected Short mapSafe(String column) {
		return Short.valueOf(column);
	}
	
	@Override
	protected Short defaultIfEmpty(String column) {
		return 0;
	}
	
	static {
		support.register(short.class, (t, u) -> new MapShortPrimitive());
	}

}
