package com.fluidapi.csv.utility;

import java.util.function.UnaryOperator;

import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;

public interface FunctionUtils {
	
	static CsvColumnMapper<?> chain(CsvColumnMapper<String> before, CsvColumnMapper<?> after) {
		if( before == null ) return after;
		if( after == null ) return before;
		return (String column) -> after.apply(before.apply(column));
	}
	
	static CsvColumnMapper<String> join(CsvColumnMapper<String> before, CsvColumnMapper<String> after) {
		if( before == null ) return after;
		if( after == null ) return before;
		return (String column) -> after.apply(before.apply(column));
	}
	
	static UnaryOperator<String> join(UnaryOperator<String> before, UnaryOperator<String> after) {
		if( before == null ) return after;
		if( after == null ) return before;
		return (String column) -> after.apply(before.apply(column));
	}
}
