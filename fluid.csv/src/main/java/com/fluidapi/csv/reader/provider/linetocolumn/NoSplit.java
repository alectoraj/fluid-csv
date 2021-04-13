package com.fluidapi.csv.reader.provider.linetocolumn;

import com.fluidapi.csv.reader.CsvLineToColumns;

public class NoSplit implements CsvLineToColumns {

	@Override
	public String[] split(String line) {
		return new String[] { line };
	}
	
}
