package test.fluidapi.csv.reader;

import static com.fluidapi.csv.reader.CsvReader.auto;
import static com.fluidapi.csv.reader.CsvReader.fixed;
import static com.fluidapi.csv.reader.CsvReader.string;
import static com.fluidapi.csv.reader.CsvReader.strip;
import static com.fluidapi.csv.utility.CollectionUtils.asSet;
import static org.apache.commons.lang3.StringUtils.isNoneEmpty;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import com.fluidapi.csv.annotations.CsvColumn;
import com.fluidapi.csv.annotations.CsvFormat;
import com.fluidapi.csv.annotations.CsvStrip;
import com.fluidapi.csv.bean.Strip;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Testable
public class TestFixedCsv {
	
	@Test
	public void testNoNulls() {
		List<String> firstNames = csv()
				.map( fixed(12, 12, 7, 9, 10) )
				.map( strip() )
				.map( string() )
				.toList();
		
		assertThat(firstNames)
			.hasSize(4)
			.containsExactly("Zeus", "Philips", "Nishen", "Yamamoto");
	}
	
	@Test
	public void testSomeNulls() {
		List<String> nations = csv()
				.map( fixed(12, 12, 7, 9, 10) )
				.map( string(4) )
				.toList();
		
		assertThat(nations)
			.hasSize(4)
			.anySatisfy(nation -> assertThat(nation).isNullOrEmpty())
			.allMatch(asSet("Olympus", "Europe", "Japan", "", null)::contains);
	}
	
	@Test
	public void testBean() {
		List<Person> nations = csv()
				.map( fixed(12, 12, 7, 9, 10) )
				.map( auto(Person.class) )
				.toList();
		
		assertThat(nations)
			.hasSize(4)
			.allMatch(person -> isNoneEmpty(person.firstName, person.lastName))
			.allSatisfy(person -> assertThat(person.age).isNotZero().isPositive())
			.anySatisfy(person -> assertThat(person.getJoining()).isNull())
			.anySatisfy(person -> assertThat(person.getNation()).isNullOrEmpty());
	}
	
	private Stream<String> csv() {
		return	"""
				Zeus        Nigoi       9012   1620JAN20Olympus
				Philips     Plodymus    5120   1842NOV7 Europe
				Nishen      Guhoi       712    1921MAR1
				Yamamoto    Kazon       1821            Japan
				"""
				.lines();
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Person {
		
		@CsvColumn(0)
		private String firstName;
		
		@CsvColumn(1)
		private String lastName;
		
		@CsvStrip(Strip.RIGHT)
		@CsvColumn(2)
		private int age;
		
		@CsvStrip
		@CsvColumn(3)
		@CsvFormat("uuuuMMMd")
		private LocalDate joining;
		
		@CsvColumn(4)
		private String nation;
		
	}
}
