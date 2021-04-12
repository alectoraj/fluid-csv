package com.fluidapi.csv;

import static com.fluidapi.csv.Csv.bigDecimal;
import static com.fluidapi.csv.Csv.delimiter;
import static com.fluidapi.csv.Csv.fixed;
import static com.fluidapi.csv.Csv.orm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import com.fluidapi.csv.bean.Person;

@Testable
public class TestCsv {
	
	@Test
	public void tryDelimiter() throws IOException {
		System.out.println("DELIMITER BY ;");
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d, uuuu");
		Files.lines(csvOfDelimiter())
			.map( delimiter(";") )
			.map( orm(Person.class) )
			.map(Person::getBirthdate)
			.map(format::format)
			.forEach(System.out::println);
	}
	
	@Test
	@Disabled
	public void tryFixed() throws IOException {
		System.out.println("FIXED OF 24, 24, 3, 20");
		Files.lines(csvOfFixed())
			.map( fixed(24, 24, 3, 20) )
			.map( bigDecimal(2) )
			.forEach(System.out::println);
	}

	private Path csvOfDelimiter() {
		return Path.of("files/test", "test-delimiter.csv");
	}
	private Path csvOfFixed() {
		return Path.of("files/test", "test-fixed.csv");
	}
	
}
