package com.fluidapi.csv;

import static com.fluidapi.csv.writer.CsvWriter.auto;
import static com.fluidapi.csv.writer.CsvWriter.delimiter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.fluidapi.csv.annotations.CsvColumn;
import com.fluidapi.csv.annotations.CsvFormat;
import com.fluidapi.csv.annotations.CsvLetterCase;
import com.fluidapi.csv.bean.LetterCase;

import lombok.Builder;
import lombok.Data;

public class QuickCheck {

	public static void main(String[] args) throws IOException {
		Stream.generate(pickGod())
		.map(auto(God.class))
		.map(delimiter(";"))
		.limit(5)
		.forEach(System.out::println);
	}
	
	@Data
	@Builder
	public static class God {
		
		@CsvColumn(0)
		private String name;
		
		@CsvColumn(1)
		private int age;
		
		@CsvColumn(2)
		@CsvFormat("MMM d, uuuu")
		@CsvLetterCase(LetterCase.UPPER)
		private LocalDate joined;
		
		@CsvColumn(3)
		@CsvLetterCase(LetterCase.LOWER)
		private String from;
		
	}
	
	private static Supplier<God> pickGod() {

		Supplier<String> name = pickName();
		Supplier<Integer> age = pickAge();
		Supplier<LocalDate> joined = pickJoined();
		Supplier<String> from = pickFrom();
		
		
		return () -> God.builder()
				.name(name.get())
				.age(age.get())
				.joined(joined.get())
				.from(from.get())
				.build();
	}
	
	private static Supplier<String> pickName() {
		Supplier<String> firstName = pickFirstNameRandom();
		Supplier<String> lastName = pickLastNameRandom();
		
		return () -> "%s %s".formatted(firstName.get(), lastName.get());
	}
	
	private static Supplier<String> pickFirstNameRandom() {
		return pick("Zeus", "Poseidon", "Hades", "Hera", "Demeter",
				"Hestia", "Hephaestus", "Ares", "Athena", "Aphrodite",
				"Hermes", "Apollo");
	}
	
	private static Supplier<String> pickLastNameRandom() {
		return pick("Adamos", "Aetos", "Agathangelou", "Andino", "Ariti",
				"Argyros", "Bakirtzis", "Baros", "Balaskas", "Bouras",
				"Castellanos", "Chaconas");
	}
	
	private static Supplier<Integer> pickAge() {
		Random random = new Random();
		return () -> random.nextInt(1000) + random.nextInt(9000);
	}
	
	private static Supplier<LocalDate> pickJoined() {
		Random random = new Random();
		return () -> LocalDate.of(
				100 + random.nextInt(1600),
				1 + random.nextInt(12),
				1 + random.nextInt(25));
	}
	
	private static Supplier<String> pickFrom() {
		return pick("Apollonia", "Byzantium", "Carystus", "Delos", "Ephesus",
				"Gorgippia", "Hermonassa", "Itanos", "Juktas", "Kepoi",
				"Lato", "Madytos", "Nysa", "Olympia", "Phaselis", "Rizinia",
				"Sparta", "Thoricus", "Vathypetros", "Zakynthos");
	}
	
	private static Supplier<String> pick(String...strings) {
		Random random = new Random();
		return () -> strings[random.nextInt(strings.length)];
	}
}
