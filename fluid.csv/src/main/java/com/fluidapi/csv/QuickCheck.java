package com.fluidapi.csv;

import static com.fluidapi.csv.reader.CsvReader.delimiter;
import static com.fluidapi.csv.writer.CsvWriter.enclose;
import static com.fluidapi.csv.writer.CsvWriter.escapeStandard;

import java.io.IOException;

import com.fluidapi.csv.writer.CsvWriter;

public class QuickCheck {

	public static void main(String[] args) throws IOException {
		String data = """
				Double Quote ";Single Quote '
				""";
		data.lines()
			.map( delimiter(";") )
			.map( escapeStandard('"', '\'') )
			.map( enclose('"') )
			.map( CsvWriter.delimiter(" | ") )
			.forEach(System.out::println);
	}
}
