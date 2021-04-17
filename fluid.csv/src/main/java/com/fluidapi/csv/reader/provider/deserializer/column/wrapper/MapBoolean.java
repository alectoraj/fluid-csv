package com.fluidapi.csv.reader.provider.deserializer.column.wrapper;

public class MapBoolean extends MapWrapper<Boolean> {

	@Override
	protected Boolean mapSafe(String column) {
		return Boolean.valueOf(column);
	}
	
	static {
		support.register(Boolean.class, (t, u) -> new MapBoolean());
	}

}
