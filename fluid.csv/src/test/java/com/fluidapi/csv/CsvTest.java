package com.fluidapi.csv;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class CsvTest {

	@Test
	public void testDrive() {
		Csv	.using(Csv.line())
			.as( Csv.string() )
			.read( Csv.tail(-3) )
			.of( Path.of("C:\\Development\\Workspaces\\FluidApis\\fluid.csv\\files\\test", "test-fixed.csv") )
			.stream()
			.forEach(System.out::println);
	}

}
