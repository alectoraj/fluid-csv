package com.fluidapi.csv.reader.provider.deserializer;

/**
 * safely picks the string value at given index, parses as integer and returns.
 * if the column value is {@code null} or empty to begin with, then {@code null} gets returned.
 * 
 * @author Arindam Biswas
 * @since 1.0
 */
public class PickInteger extends PickNumber<Integer> {

	public PickInteger(int index) {
		super(index, Integer::valueOf);
	}

}
