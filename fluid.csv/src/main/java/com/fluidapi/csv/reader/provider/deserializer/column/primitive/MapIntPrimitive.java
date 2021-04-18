package com.fluidapi.csv.reader.provider.deserializer.column.primitive;

public class MapIntPrimitive extends MapPrimitive<Integer> {

	@Override
	protected Integer mapSafe(String column) {
		return Integer.valueOf(column);
	}
	
	@Override
	protected Integer defaultIfEmpty(String column) {
		return 0;
	}
	
	static {
		support.register(int.class, (t, u) -> new MapIntPrimitive());
	}

}
