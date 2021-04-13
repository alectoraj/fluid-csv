package com.fluidapi.csv.bean;

import static com.fluidapi.csv.validaton.Requires.failIf;

public record Quote(char start, char end, char escape) {
	
	public static final char NULL = '\0';
	
	public Quote {
		failIf(escape == NULL, "invalid escape character");
		failIf(start == escape, "escape character must be different than quotes");
		failIf(end == escape, "escape character must be different than quotes");
	}
	
}
