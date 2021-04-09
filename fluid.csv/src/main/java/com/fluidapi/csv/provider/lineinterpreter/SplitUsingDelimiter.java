package com.fluidapi.csv.provider.lineinterpreter;

import com.fluidapi.csv.CsvLineInterpreter;
import com.fluidapi.csv.bean.CsvInterpretedLine;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SplitUsingDelimiter extends AbstractCsvLineInterpreter implements CsvLineInterpreter {
	
	@NonNull
	protected final String delimiter;
	
	@Override
	public CsvInterpretedLine interpretSafe(String line) {
		return CsvInterpretedLine.of(line.split(delimiter));
	}

}
