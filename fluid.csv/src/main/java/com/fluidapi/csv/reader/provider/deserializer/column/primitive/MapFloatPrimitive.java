package com.fluidapi.csv.reader.provider.deserializer.column.primitive;

public class MapFloatPrimitive extends MapPrimitive<Float> {

	@Override
	protected Float mapSafe(String column) {
		return Float.valueOf(column);
	}
	
	@Override
	protected Float defaultIfEmpty(String column) {
		return 0f;
	}
	
	static {
		support.register(float.class, (t, u) -> new MapFloatPrimitive());
	}

}
