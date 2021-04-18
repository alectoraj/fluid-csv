package com.fluidapi.csv.reader.provider.deserializer.column.number;

import java.util.concurrent.atomic.AtomicInteger;

public class MapAtomicInteger extends MapNumber<AtomicInteger> {
	
	final MapInteger toInt;
	
	public MapAtomicInteger() {
		toInt = new MapInteger();
	}

	@Override
	protected AtomicInteger mapSafe(String column) {
		return new AtomicInteger(toInt.map(column));
	}
	
	static {
		support.register(AtomicInteger.class, (t, u) -> new MapAtomicInteger());
	}
}
