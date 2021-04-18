package com.fluidapi.csv.reader.provider.deserializer.column.number;

import java.math.BigDecimal;

public class MapBigDecimal extends MapNumber<BigDecimal> {

	@Override
	protected BigDecimal mapSafe(String column) {
		return new BigDecimal(column);
	}
	
	static {
		support.register(BigDecimal.class, (t, u) -> new MapBigDecimal());
	}

}
