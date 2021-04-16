package com.fluidapi.csv.reader.provider.deserializer.column;

public class MapStringTrimmed extends MapSafe<String> {

	@Override
	protected String mapSafe(String column) {
		return column.trim();
	}

}
