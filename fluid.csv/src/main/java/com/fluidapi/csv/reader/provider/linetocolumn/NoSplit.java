package com.fluidapi.csv.reader.provider.linetocolumn;

import com.fluidapi.csv.reader.CsvLineToColumns;
import com.fluidapi.csv.reader.CsvReader;

/**
 * Reads the whole line as a single column.
 * 
 * @author Arindam Biswas
 * @since 1.0
 * 
 * @see CsvReader#line()
 */
public class NoSplit implements CsvLineToColumns {

	@Override
	public String[] split(String line) {
		return new String[] { line };
	}
	
}
