package com.fluidapi.csv.reader.provider.deserializer.column.primitive;

public class MapBooleanPrimitive extends MapPrimitive<Boolean> {

	@Override
	protected Boolean mapSafe(String column) {
		return Boolean.valueOf(column);
	}
	
	@Override
	protected Boolean defaultIfEmpty() {
		return false;
	}
	
	static {
		support.register(boolean.class, (t, u) -> new MapBooleanPrimitive());
	}

}
