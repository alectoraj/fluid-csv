package com.fluidapi.csv.reader.provider.deserializer;

import com.fluidapi.csv.reader.provider.deserializer.column.MapInteger;

/**
 * safely picks the string value at given index, parses as integer and returns.
 * if the column value is {@code null} or empty to begin with, then {@code null} gets returned.
 * 
 * @author Arindam Biswas
 * @since 1.0
 */
public class PickInteger extends PickMapped<Integer> {

	public PickInteger(int index) {
		super(index, new MapInteger());
	}

}
