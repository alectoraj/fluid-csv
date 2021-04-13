package com.fluidapi.csv.bean;

import java.time.LocalDate;

import com.fluidapi.csv.annotations.CsvColumn;
import com.fluidapi.csv.annotations.CsvFormat;
import com.fluidapi.csv.annotations.StripString;

import lombok.Data;

@Data
public class Person {
	
	@CsvColumn(0)
	private String firstname;
	
	@CsvColumn(1)
	private String lastname;

	@CsvColumn(2)
	private int age;
	
	@CsvColumn(3)
	@StripString
	private String location;
	
	@CsvColumn(4)
	@CsvFormat("MMM d, uuuu")
	private LocalDate birthdate;
	
}
