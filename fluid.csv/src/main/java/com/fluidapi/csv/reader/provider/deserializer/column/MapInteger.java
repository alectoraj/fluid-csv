package com.fluidapi.csv.reader.provider.deserializer.column;

public class MapInteger extends MapNumber<Integer> {
	
	public MapInteger() {
		super(Integer::valueOf);
	}

}
