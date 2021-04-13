package com.fluidapi.csv;

import static com.fluidapi.csv.reader.CsvReader.dequote;

import java.util.Arrays;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class QuickCheck {

	public static void main(String[] args) {
		
		quoteCsv().map(dequote('"'))
		.map(Arrays::asList)
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
				Nishen      Guhoi       712    1921MAR1
				Yamamoto    Kazon       1821            Japan
				"""
				.lines();
	}
}
