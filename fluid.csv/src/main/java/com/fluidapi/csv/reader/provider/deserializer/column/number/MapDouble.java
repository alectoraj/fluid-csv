package com.fluidapi.csv.reader.provider.deserializer.column.number;

public class MapDouble extends MapNumber<Double> {

	@Override
	protected Double mapSafe(String column) {
		return Double.valueOf(column);
	}
	
	static {
		support.register(Double.class, (t, u) -> new MapDouble());
	}
}
