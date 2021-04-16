package com.fluidapi.csv.reader.provider.deserializer;

import java.math.BigDecimal;

import com.fluidapi.csv.reader.provider.deserializer.column.number.MapBigDecimal;

/**
 * safely picks the string value at given index, parses as {@link BigDecimal} and returns.
 * if the column value is {@code null} or empty to begin with, then {@code null} gets returned.
 * 
 * @author Arindam Biswas
 * @since 1.0
 */
public class PickBigDecimal extends PickMapped<BigDecimal> {

	public PickBigDecimal(int index) {
		super(index, new MapBigDecimal());
	}

}
