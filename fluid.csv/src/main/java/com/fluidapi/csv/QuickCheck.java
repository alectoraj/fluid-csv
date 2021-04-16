package com.fluidapi.csv;

import static com.fluidapi.csv.reader.CsvReader.auto;
import static com.fluidapi.csv.reader.CsvReader.fixed;

import java.time.LocalDate;
import java.util.stream.Stream;

import com.fluidapi.csv.annotations.CsvColumn;
import com.fluidapi.csv.annotations.CsvDeserializer;
import com.fluidapi.csv.annotations.CsvFormat;
import com.fluidapi.csv.annotations.CsvStrip;
import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("unused")
public class QuickCheck {

	public static void main(String[] args) {
//		runCsv();
	}

	private static void runCsv() {
		fixCsv().map(fixed(12, 12, 7, 9, 10))
//		.map(strip())
		.map(auto(God.class))
		.forEach(System.out::println);
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

		private int ageMinusX;

		@CsvStrip
		@CsvColumn(3)
		@CsvFormat("uuuuMMMd")
		private LocalDate joined;
		
		@CsvColumn(4)
		private String from;

		@CsvStrip
		@CsvColumn(2)
		@CsvDeserializer(AgeDeserializer.class)
		public void setAltAge(int age) {
			ageMinusX = age;
		}
		
	}
	
	public static class AgeDeserializer implements CsvColumnMapper<Integer> {

		@Override
		public Integer map(String column) {
			return Integer.parseInt(column) - 1000;
		}
		
	}
}
