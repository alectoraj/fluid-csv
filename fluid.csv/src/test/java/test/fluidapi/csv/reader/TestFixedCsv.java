package test.fluidapi.csv.reader;

import static com.fluidapi.csv.reader.CsvReader.auto;
import static com.fluidapi.csv.reader.CsvReader.delimiter;
import static com.fluidapi.csv.reader.CsvReader.string;
import static org.apache.commons.lang3.StringUtils.isNoneEmpty;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import com.fluidapi.csv.annotations.CsvColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Testable
public class TestFixedCsv {
	
	@Test
	public void testNoNulls() {
		List<String> firstNames = csv()
				.map( delimiter(";") )
				.map( string() )
				.toList();
		
		assertThat(firstNames)
			.hasSize(4)
			.containsExactly("Zeus", "Philips", "Nishen", "Yamamoto");
	}
	
	@Test
	@Disabled("Waiting for implementation")
	public void testSomeNulls() {
		List<String> nations = csv()
				.map( delimiter(";") )
				.map( string(4) )
				.toList();
		
		assertThat(nations)
			.hasSize(4)
			.anySatisfy(nation -> assertThat(nation).isEmpty())
			.containsOnly("Olympus", "Europe", "Japan", "", null);
	}
	
	@Test
	@Disabled("Waiting for implementation")
	public void testBean() {
		List<Person> nations = csv()
				.map( delimiter(";") )
				.map( auto(Person.class) )
				.toList();
		
		assertThat(nations)
			.hasSize(4)
			.noneMatch(person -> isNoneEmpty(person.firstName, person.lastName))
			.noneSatisfy(person -> assertThat(person.age).isNotZero().isPositive())
			.anySatisfy(person -> assertThat(person.getJoining()).isNull())
			.anySatisfy(person -> assertThat(person.getNation()).isEmpty());
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
		
		@CsvColumn(2)
		private int age;
		
		@CsvColumn(3)
		private LocalDate joining;
		
		@CsvColumn(4)
		private String nation;
		
	}
}
