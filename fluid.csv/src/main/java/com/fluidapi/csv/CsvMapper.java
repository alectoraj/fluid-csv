package com.fluidapi.csv;

@FunctionalInterface
public interface CsvMapper<T> {
	
	CsvReader<T> read( CsvContentReader content );
	
}
