package com.fluidapi.csv;

import static com.fluidapi.csv.reader.CsvReader.auto;
import static com.fluidapi.csv.reader.CsvReader.delimiter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;

import com.fluidapi.csv.annotations.CsvColumn;
import com.fluidapi.csv.annotations.CsvFormat;
import com.fluidapi.csv.annotations.CsvToUpperCase;

import lombok.Data;

public class QuickCheck {

	public static void main(String[] args) throws IOException {
		Path testFile = Path.of("C:\\Users\\rajbi\\Downloads\\testing\\5m Sales Records.csv");
		
		Instant start = Instant.now();
		Files.lines(testFile)
			.skip(1) // header
			.parallel()
			.map( delimiter(",") )
			.map( auto(Sales.class) )
//			.min(Comparator.comparing(Sales::getOrderDate))
			.reduce((a, b) -> b)
			.ifPresent(System.out::println);
		Instant end = Instant.now();
		System.out.println(Duration.between(start, end));
	}

	@Data
	public static class Sales {
		
		@CsvToUpperCase
		@CsvColumn(0)
		private String region;

		@CsvColumn(1)
		private String country;

		@CsvColumn(2)
		private String itemType;

		@CsvColumn(3)
		private String salesChannel;

		@CsvColumn(4)
		private Character orderPriority;

		@CsvColumn(5)
		@CsvFormat("M/d/uuuu")
		private LocalDate orderDate;

		@CsvColumn(6)
		private Integer orderID;

		@CsvColumn(7)
		@CsvFormat("M/d/uuuu")
		private LocalDate shipDate;

		@CsvColumn(8)
		private Integer unitsSold;

		@CsvColumn(9)
		private Double unitPrice;

		@CsvColumn(10)
		private Double unitCost;

		@CsvColumn(11)
		private Double totalRevenue;

		@CsvColumn(12)
		private Double totalCost;

		@CsvColumn(13)
		private Double totalProfit;
		
	}
}
