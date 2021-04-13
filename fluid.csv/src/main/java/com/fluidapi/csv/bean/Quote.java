package com.fluidapi.csv.bean;

public record Quote(char start, char end, char escape) {
	
	public Quote {
		// TODO validate start != escape
		// TODO validate end != escape
	}
	
}
