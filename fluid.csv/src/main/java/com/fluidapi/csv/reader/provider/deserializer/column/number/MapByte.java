package com.fluidapi.csv.reader.provider.deserializer.column.number;

public class MapByte extends MapNumber<Byte> {

	@Override
	protected Byte mapSafe(String column) {
		return Byte.valueOf(column);
	}
	
	static {
		support.register(Byte.class, (t, u) -> new MapByte());
	}
}
