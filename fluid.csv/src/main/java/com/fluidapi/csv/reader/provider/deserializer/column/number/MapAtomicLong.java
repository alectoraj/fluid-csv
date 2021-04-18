package com.fluidapi.csv.reader.provider.deserializer.column.number;

import java.util.concurrent.atomic.AtomicLong;

public class MapAtomicLong extends MapNumber<AtomicLong> {
	
	final MapLong toLong;
	
	public MapAtomicLong() {
		toLong = new MapLong();
	}

	@Override
	protected AtomicLong mapSafe(String column) {
		return new AtomicLong(toLong.map(column));
	}
	
	static {
		support.register(AtomicLong.class, (t, u) -> new MapAtomicLong());
	}
}
