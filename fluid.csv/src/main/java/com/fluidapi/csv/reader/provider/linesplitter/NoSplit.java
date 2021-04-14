package com.fluidapi.csv.reader.provider.linesplitter;

import com.fluidapi.csv.reader.CsvLineSplitter;
import com.fluidapi.csv.reader.CsvReader;

/**
 * Reads the whole line as a single column.
 * 
 * @author Arindam Biswas
 * @since 1.0
 * 
 * @see CsvReader#line()
 */
public class NoSplit implements CsvLineSplitter {

	@Override
	public String[] split(String line) {
		return new String[] { line };
	}
	
}
