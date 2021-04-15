package com.fluidapi.csv.reader.provider.deserializer.column;

public class MapStringStripped extends MapSafe<String> {

	public MapStringStripped() {
		super(String::strip);
	}

}
