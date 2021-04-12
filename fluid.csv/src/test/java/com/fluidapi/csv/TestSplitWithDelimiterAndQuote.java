package com.fluidapi.csv;

import static com.fluidapi.csv.Csv.delimiter;
import static com.fluidapi.csv.Csv.orm;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import com.fluidapi.csv.config.CsvColumn;

import lombok.Data;

@Testable
public class TestSplitWithDelimiterAndQuote {
	
	@Test
	public void testSimple() {
		Stream.of(
			"\"A01\", \"B01\", \"1, 2, 4\", \"D01\"",
			"\"A02\", \"B02\", \"2, 4, 8\", \"D\\\"02\"",
			"\"A03\", \"B03\", \"3, 6, 9\", \"\""
		)
		.map( delimiter(',', '"') )
		.map( orm(BeanSimple.class) )
		.forEach(System.out::println);
	}
	
	@Data
	public static class BeanSimple {
		private @CsvColumn(0) String first;
		private @CsvColumn(1) String second;
		private @CsvColumn(2) String third;
		private @CsvColumn(3) String forth;
	}
	
}
