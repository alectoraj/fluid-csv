package com.fluidapi.csv.bean;

import com.fluidapi.csv.config.CsvColumn;

import lombok.Data;

@Data
public class Person {
	
	@CsvColumn(0)
	private String firstname;
	
	@CsvColumn(1)
	private String lastname;
	
	private int age;
	
	@CsvColumn(3)
	private String location;
	
	@CsvColumn(2)
	public void setAge(String age) {
		this.age = Integer.parseInt(age);
	}
}
