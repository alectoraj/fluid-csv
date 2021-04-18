package com.fluidapi.csv.reader.provider.deserializer.column.primitive;

public class MapBytePrimitive extends MapPrimitive<Byte> {

	@Override
	protected Byte mapSafe(String column) {
		return Byte.valueOf(column);
	}
	
	@Override
	protected Byte defaultIfEmpty(String column) {
		return 0;
	}
	
	static {
		support.register(byte.class, (t, u) -> new MapBytePrimitive());
	}

}
