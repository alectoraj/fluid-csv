package com.fluidapi.csv.reader.provider.deserializer.column.number;

import java.math.BigInteger;

public class MapBigInteger extends MapNumber<BigInteger> {

	@Override
	protected BigInteger mapSafe(String column) {
		return new BigInteger(column);
	}
	
	static {
		support.register(BigInteger.class, (t, u) -> new MapBigInteger());
	}

}
