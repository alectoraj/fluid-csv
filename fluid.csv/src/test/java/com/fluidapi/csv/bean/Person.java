package com.fluidapi.csv.bean;

import java.time.LocalDate;

import com.fluidapi.csv.config.CsvColumn;
import com.fluidapi.csv.config.StripString;

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
	private LocalDate birthdate;
	
}
