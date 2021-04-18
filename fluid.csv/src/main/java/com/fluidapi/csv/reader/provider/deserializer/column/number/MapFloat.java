package com.fluidapi.csv.reader.provider.deserializer.column.number;

public class MapFloat extends MapNumber<Float> {

	@Override
	protected Float mapSafe(String column) {
		return Float.valueOf(column);
	}
	
	static {
		support.register(Float.class, (t, u) -> new MapFloat());
	}
}
