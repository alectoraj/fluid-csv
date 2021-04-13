package com.fluidapi.csv;

import static com.fluidapi.csv.Csv.delimiter;
import static com.fluidapi.csv.Csv.orm;
import static com.fluidapi.csv.Csv.strip;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.NOVEMBER;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import com.fluidapi.csv.config.CsvColumn;
import com.fluidapi.csv.config.CsvFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Testable
public class TestCsvByDelimiter {
	
	@Test
	public void testSimple() {
		List<Employee> employees = Stream.of(
			"Zeus Dev; 5290; Jan 15, 1650; Olympus",
			"Saint Archimidis; 721; Nov 22, 1834; Greece",
			"Zig Buffalo; 1982; Jul 1, 1720",
			"Kumin Surter; 90;"
		)
		.map( delimiter(";") )
		.map( strip() )
		.map( orm(Employee.class) )
		.toList();
		
		assertThat(employees.size(), is(4));
		
		Iterator<Employee> e = employees.iterator();
		testEmployeeSame(e.next(), Employee.of("Zeus Dev", 5290, LocalDate.of(1650, JANUARY, 15), "Olympus"));
		testEmployeeSame(e.next(), Employee.of("Saint Archimidis", 721, LocalDate.of(1834, NOVEMBER, 22), "Greece"));
		testEmployeeSame(e.next(), Employee.of("Zig Buffalo", 1982, LocalDate.of(1720, JULY, 1), null));
		testEmployeeSame(e.next(), Employee.of("Kumin Surter", 90, null, null));
	}

	protected void testEmployeeSame(Employee actual, Employee expected) {
		assertThat(actual, allOf(
				notNullValue(),
				isA(Employee.class),
				is(expected)
		));
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor(staticName = "of")
	public static class Employee {
		
		@CsvColumn(0)
		private String name;
		
		@CsvColumn(1)
		private int age;
		
		@CsvColumn(2)
		@CsvFormat("MMM d, uuuu")
		private LocalDate joined;
		
		@CsvColumn(3)
		private String location;
		
	}

}
