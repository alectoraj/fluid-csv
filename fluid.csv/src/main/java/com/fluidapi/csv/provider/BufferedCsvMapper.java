package com.fluidapi.csv.provider;

import com.fluidapi.csv.CsvContentReader;
import com.fluidapi.csv.CsvLineInterpreter;
import com.fluidapi.csv.CsvMapper;
import com.fluidapi.csv.CsvReader;
import com.fluidapi.csv.CsvRowMapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BufferedCsvMapper<T> implements CsvMapper<T> {

	protected final @NonNull CsvLineInterpreter lineSplitter;
	protected final @NonNull CsvRowMapper<T> rowMapper;

	@Override
	public CsvReader<T> read(CsvContentReader content) {
		return new BufferedCsvReader<>(lineSplitter, rowMapper, content);
	}

}
