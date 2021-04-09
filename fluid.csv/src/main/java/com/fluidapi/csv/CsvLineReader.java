package com.fluidapi.csv;

@FunctionalInterface
public interface CsvLineReader {
	
	<T> CsvMapper<T> as( CsvRowMapper<T> rowMapper );
	
}
