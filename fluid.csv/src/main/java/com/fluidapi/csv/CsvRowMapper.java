package com.fluidapi.csv;

import static java.util.Objects.requireNonNull;

import java.util.function.Function;

import com.fluidapi.csv.bean.CsvInterpretedLine;

@FunctionalInterface
public interface CsvRowMapper<T> {

	T map(CsvInterpretedLine interpretedLine);
	
	default <U> CsvRowMapper<U> then(Function<T, U> convert) {
		requireNonNull(convert, "convertion function");
		return line -> convert.apply(this.map(line));
	}
}
