package com.fluidapi.csv.provider.rowmapper;

import static com.fluidapi.csv.validator.Requires.requiresTrue;

import com.fluidapi.csv.CsvRowMapper;
import com.fluidapi.csv.bean.CsvInterpretedLine;

public class PickStringColumn implements CsvRowMapper<String> {
	
	protected final int index;
	
	/**
	 * @param column 0 based index of the column to be picked
	 */
	public PickStringColumn(int column) {
		requiresTrue(0 <= column, "not an index");
		index = column;
	}

	@Override
	public String map(CsvInterpretedLine line) {
		return line.nullIfAbsent(index);
	}

}
