package com.fluidapi.csv.reader.provider.deserializer.column.primitive;

public class MapLongPrimitive extends MapPrimitive<Long> {

	@Override
	protected Long mapSafe(String column) {
		return Long.valueOf(column);
	}
	
	@Override
	protected Long defaultIfEmpty() {
		return 0L;
	}
	
	static {
		support.register(long.class, (t, u) -> new MapLongPrimitive());
	}

}
