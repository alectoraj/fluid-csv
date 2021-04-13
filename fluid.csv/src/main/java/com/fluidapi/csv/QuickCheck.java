package com.fluidapi.csv;

import static com.fluidapi.csv.reader.CsvReader.delimiter;

import java.util.Arrays;
import java.util.stream.Stream;

public class QuickCheck {

	public static void main(String[] args) {
		csv().map(delimiter(";"))
		.map(Arrays::asList)
		.forEach(System.out::println);
	}

	private static Stream<String> csv() {
		return	"""
				Zeus;Nigoi;9012;1620JAN20;Olympus
				Philips;Plodymus;5120;1842NOV12;Europe
				Nishen;Guhoi;712;1921MAR1
				Yamamoto;Kazon;1821;;Japan
				"""
				.lines();
	}
}
