package com.fluidapi.csv.provider;

import java.nio.file.Path;

import com.fluidapi.csv.CsvContentReader;
import com.fluidapi.csv.CsvLineInterpreter;
import com.fluidapi.csv.CsvReader;
import com.fluidapi.csv.CsvRowMapper;
import com.fluidapi.csv.function.Sequence;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BufferedCsvReader<T> implements CsvReader<T> {
	
	protected final @NonNull CsvLineInterpreter lineSplitter;
	protected final @NonNull CsvRowMapper<T> rowMapper;
	protected final @NonNull CsvContentReader fileReader;
	
	@Override
	public Sequence<T> of(Path path) {
		return fileReader.lines(path)
				.then(lineSplitter::interpret)
				.then(rowMapper::map);
	}

}
