package com.fluidapi.csv.reader.provider.deserializer.column.primitive;

import static com.fluidapi.csv.validaton.FailCheck.failIf;

public class MapCharacterPrimitive extends MapPrimitive<Character> {

	@Override
	protected Character mapSafe(String column) {
		// safe, so column.length() will always be > 0 at this point
		failIf(column.length() != 1, "character column must be of a single character");
		return column.charAt(0);
	}
	
	@Override
	protected Character defaultIfEmpty() {
		return ' '; // black space
	}
	
	static {
		support.register(char.class, (t, u) -> new MapCharacterPrimitive());
	}

}
