package com.fluidapi.csv.reader.provider.deserializer.column;

public class MapStringStripped extends MapSafe<String> {

	@Override
	protected String mapSafe(String column) {
		return column.strip();
	}

}
