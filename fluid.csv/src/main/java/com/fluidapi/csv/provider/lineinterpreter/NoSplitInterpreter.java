package com.fluidapi.csv.provider.lineinterpreter;

import com.fluidapi.csv.CsvLineInterpreter;
import com.fluidapi.csv.bean.CsvInterpretedLine;

public class NoSplitInterpreter extends AbstractCsvLineInterpreter implements CsvLineInterpreter {
	
	@Override
	public CsvInterpretedLine interpretSafe(String line) {
		return CsvInterpretedLine.of(line);
	}

}
