package com.fluidapi.csv;

import java.nio.file.Path;

import com.fluidapi.csv.function.Sequence;

@FunctionalInterface
public interface CsvReader<T> {

	Sequence<T> of( Path path );
	
}
