package com.fluidapi.csv;

import static com.fluidapi.csv.Csv.delimiter;
import static com.fluidapi.csv.Csv.fixed;
import static com.fluidapi.csv.Csv.fixedStripped;
import static com.fluidapi.csv.Csv.orm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import com.fluidapi.csv.bean.Person;

@Testable
public class TestCsv {
	
	@Test
	public void tryDelimiter() throws IOException {
		System.out.println("DELIMITER BY ;");
		Files.lines(csvOfDelimiter())
			.map( delimiter(";") )
			.map( orm(Person.class) )
			.forEach(System.out::println);
	}
	
	@Test
	public void tryFixed() throws IOException {
		System.out.println("FIXED OF 24, 24, 3, 20, 10");
		Files.lines(csvOfFixed())
			.map( fixed(24, 24, 3, 20, 10) )
			.map( orm(Person.class) )
			.forEach(System.out::println);
	}
	
	@Test
	public void tryFixedStripped() throws IOException {
		System.out.println("STRIPPED FIXED OF 24, 24, 3, 20, 10");
		Files.lines(csvOfFixed())
			.map( fixedStripped(24, 24, 3, 20, 10) )
			.map( orm(Person.class) )
			.forEach(System.out::println);
	}

	private Path csvOfDelimiter() {
		return Path.of("files/test", "test-delimiter.csv");
	}
	private Path csvOfFixed() {
		return Path.of("files/test", "test-fixed.csv");
	}
	
}
