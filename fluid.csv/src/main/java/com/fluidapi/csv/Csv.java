package com.fluidapi.csv;

import java.math.BigDecimal;

import com.fluidapi.csv.internal.beans.Quotes;
import com.fluidapi.csv.internal.mapper.provider.AutoMapToBean;
import com.fluidapi.csv.internal.mapper.provider.PickStringColumn;
import com.fluidapi.csv.internal.splitter.provider.NoSplitLine;
import com.fluidapi.csv.internal.splitter.provider.SplitByDelimiter;
import com.fluidapi.csv.internal.splitter.provider.SplitByDelimiterWithQuotes;
import com.fluidapi.csv.internal.splitter.provider.SplitByFixedLengths;
import com.fluidapi.csv.internal.splitter.provider.SplitByFixedLengthsThenStrip;

public class Csv {
	
	// LINE SPLITTERS //

	public static LineSplitter delimiter(String regex) {
		return new SplitByDelimiter(regex);
	}
	public static LineSplitter delimiter(char delimiter, char quotation) {
		return delimiter(delimiter, quotation, quotation);
	}
	public static LineSplitter delimiter(char delimtier, char quoteStart, char quoteEnd) {
		return new SplitByDelimiterWithQuotes(new Quotes(delimtier, quoteStart, quoteEnd));
	}
	public static LineSplitter fixed(int...lengths) {
		return new SplitByFixedLengths(lengths);
	}
	public static LineSplitter fixedStripped(int...lengths) {
		return new SplitByFixedLengthsThenStrip(lengths);
	}
	public static LineSplitter line() {
		return new NoSplitLine();
	}
	
	// ORM MAPPERS //

	public static OrmMapper<String> string() {
		return string(0);
	}
	public static OrmMapper<String> string(int index) {
		return new PickStringColumn(index);
	}

	public static OrmMapper<String> stripped() {
		return stripped(0);
	}
	public static OrmMapper<String> stripped(int index) {
		return string(index).then(String::strip);
	}
	
	public static OrmMapper<Integer> integer() {
		return integer(0);
	}
	public static OrmMapper<Integer> integer(int index) {
		return stripped(index).then(Integer::valueOf);
	}
	
	public static OrmMapper<BigDecimal> bigDecimal() {
		return bigDecimal(0);
	}
	public static OrmMapper<BigDecimal> bigDecimal(int index) {
		return stripped(index).then(BigDecimal::new);
	}

	public static <T> OrmMapper<T> orm(Class<T> type) {
		return new AutoMapToBean<>(type);
	}
	
}
