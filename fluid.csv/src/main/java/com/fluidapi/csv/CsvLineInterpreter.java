package com.fluidapi.csv;

import com.fluidapi.csv.bean.CsvInterpretedLine;

@FunctionalInterface
public interface CsvLineInterpreter {
	
	CsvInterpretedLine interpret( String line );
	
}
