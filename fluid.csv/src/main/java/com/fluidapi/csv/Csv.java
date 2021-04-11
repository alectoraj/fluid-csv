package com.fluidapi.csv;

import java.math.BigDecimal;

import com.fluidapi.csv.internal.mapper.provider.AutoMapToBean;
import com.fluidapi.csv.internal.mapper.provider.PickStringColumn;
import com.fluidapi.csv.internal.splitter.provider.NoSplitLine;
import com.fluidapi.csv.internal.splitter.provider.SplitByDelimiter;
import com.fluidapi.csv.internal.splitter.provider.SplitByFixedLengths;

public class Csv {
	
	// LINE SPLITTERS //

	public static LineSplitter delimiter(String regex) {
		return SplitByDelimiter.using(regex);
	}
	public static LineSplitter fixed(int...lengths) {
		return SplitByFixedLengths.using(lengths);
	}
	public static LineSplitter line() {
		return NoSplitLine.get();
	}
	
	// ORM MAPPERS //

	public static OrmMapper<String> string() {
		return string(0);
	}
	public static OrmMapper<String> string(int index) {
		return PickStringColumn.pick(index);
	}
	
	public static OrmMapper<Integer> integer() {
		return integer(0);
	}
	public static OrmMapper<Integer> integer(int index) {
		return string(index).then(String::strip).then(Integer::valueOf);
	}
	
	public static OrmMapper<BigDecimal> bigDecimal() {
		return bigDecimal(0);
	}
	public static OrmMapper<BigDecimal> bigDecimal(int index) {
		return string(index).then(String::strip).then(BigDecimal::new);
	}

	public static <T> OrmMapper<T> orm(Class<T> type) {
		return AutoMapToBean.of(type);
	}
	
}
