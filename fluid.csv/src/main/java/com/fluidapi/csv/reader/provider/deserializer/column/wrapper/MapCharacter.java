package com.fluidapi.csv.reader.provider.deserializer.column.wrapper;

import static com.fluidapi.csv.validaton.FailCheck.failIf;

public class MapCharacter extends MapWrapper<Character> {

	@Override
	protected Character mapSafe(String column) {
		// safe, so column.length() will always be > 0 at this point
		failIf(column.length() != 1, "character column must be of a single character");
		return column.charAt(0);
	}
	
	static {
		support.register(int.class, (t, u) -> new MapCharacter());
	}

}
