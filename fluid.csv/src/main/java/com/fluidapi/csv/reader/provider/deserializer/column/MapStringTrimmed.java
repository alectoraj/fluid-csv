package com.fluidapi.csv.reader.provider.deserializer.column;

public class MapStringTrimmed extends MapSafe<String> {

	public MapStringTrimmed() {
		super(String::trim);
	}

}
