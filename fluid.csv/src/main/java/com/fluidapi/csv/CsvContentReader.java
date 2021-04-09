package com.fluidapi.csv;

import java.nio.file.Path;

import com.fluidapi.csv.function.Sequence;

public interface CsvContentReader {
	
	Sequence<String> lines( Path file );
	
	default CsvContentReader then(CsvContentReader reader) {
		// TODO combine
		return reader;
	}
	
}
