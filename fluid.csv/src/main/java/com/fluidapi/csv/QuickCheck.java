package com.fluidapi.csv;

import static com.fluidapi.csv.reader.CsvReader.auto;
import static com.fluidapi.csv.reader.CsvReader.delimiter;
import static com.fluidapi.csv.reader.CsvReader.fixed;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.stream.Stream;

import com.fluidapi.csv.annotations.CsvColumn;
import com.fluidapi.csv.annotations.CsvFormat;
import com.fluidapi.csv.annotations.CsvStrip;

import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("unused")
public class QuickCheck {

	public static void main(String[] args) {
//		runCsv();
		runCsv2();
	}

	private static void runCsv2() {
		delimCsv2().map(delimiter(";"))
		.map(auto(MonthAndYear.class))
		.forEach(System.out::println);
	}

	private static void runCsv() {
		fixCsv().map(fixed(12, 12, 7, 9, 10))
//		.map(strip())
		.map(auto(God.class))
		.forEach(System.out::println);
	}
	
	private static Stream<String> delimCsv2() {
		return """
				JAN;2020
				FEBRUARY;2018
				3;2010
				05;2010
				"""
				.lines();
	}

	private static Stream<String> delimCsv() {
		return	"""
				Zeus;Nigoi;9012;1620JAN20;Olympus
				Philips;Plodymus;5120;1842NOV12;Europe
				Nishen;Guhoi;712;1921MAR1
				Yamamoto;Kazon;1821;;Japan
				"""
				.lines();
	}

	private static Stream<String> quoteCsv() {
		return	"""
				"Zeus";"Nigoi";"9012";"1620JAN20";"Olympus"
				"Philips";"Plodymus";"5120";"1842NOV12";"Europe"
				"Nishen";"Guhoi";"712";"1921MAR1"
				"Yamamoto";"Kazon";"1821";"";"Japan"
				"""
				.lines();
	}
	
	private static Stream<String> fixCsv() {
		return	"""
				Zeus        Nigoi       9012   1620JAN20Olympus
				Philips     Plodymus    5120   1842NOV7 Europe
				Nishen      Guhoi       724    1921MAR1
				Yamamoto    Kazon       1821            Japan
				"""
				.lines();
	}
	
	@Data
	@NoArgsConstructor
	public static class God {
		
		@CsvColumn(0)
		private String firstName;

		@CsvStrip
		@CsvColumn(1)
		private String lastName;
		
		@CsvStrip
		@CsvColumn(2)
		private int age;

		@CsvStrip
		@CsvColumn(3)
		@CsvFormat("uuuuMMMd")
		private LocalDate joined;
		
		@CsvColumn(4)
		private String from;
		
	}
	
	@Data
	public static class MonthAndYear {
		
		@CsvColumn(0)
		private Month month;
		
		@CsvColumn(1)
		private Year year;
	}
}
