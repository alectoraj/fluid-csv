package com.fluidapi.csv.provider;

import com.fluidapi.csv.CsvLineInterpreter;
import com.fluidapi.csv.CsvLineReader;
import com.fluidapi.csv.CsvMapper;
import com.fluidapi.csv.CsvRowMapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BufferedCsvLineReader implements CsvLineReader {
	
	protected final @NonNull CsvLineInterpreter lineSplitter;
	
	@Override
	public <T> CsvMapper<T> as(CsvRowMapper<T> rowMapper) {
		return new BufferedCsvMapper<>(lineSplitter, rowMapper);
	}
}
