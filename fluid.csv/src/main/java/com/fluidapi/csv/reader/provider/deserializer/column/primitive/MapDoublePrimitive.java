package com.fluidapi.csv.reader.provider.deserializer.column.primitive;

public class MapDoublePrimitive extends MapPrimitive<Double> {

	@Override
	protected Double mapSafe(String column) {
		return Double.valueOf(column);
	}
	
	@Override
	protected Double defaultIfEmpty(String column) {
		return 0D;
	}
	
	static {
		support.register(double.class, (t, u) -> new MapDoublePrimitive());
	}

}
