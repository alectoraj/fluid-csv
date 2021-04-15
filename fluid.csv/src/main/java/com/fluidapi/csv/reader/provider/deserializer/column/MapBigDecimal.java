package com.fluidapi.csv.reader.provider.deserializer.column;

import java.math.BigDecimal;

public class MapBigDecimal extends MapNumber<BigDecimal> {

	public MapBigDecimal() {
		super(BigDecimal::new);
	}

}
