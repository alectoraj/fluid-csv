package com.fluidapi.csv.bean;

import static com.fluidapi.csv.validaton.FailCheck.failIf;

/**
 * Record to hold start and end of a quoted section,
 * including an escape character.
 * Puts restrictions like
 * <ol>
 * <li>escape character cannot be null character</li>
 * <li>escape characters cannot be same as quote character</li>
 * </ol>
 * 
 * @author Arindam Biswas
 * @since 1.0
 */
public record Quote(char start, char end, char escape) {
	
	public static final char NULL = '\0';
	
	public Quote {
		failIf(escape == NULL, "invalid escape character");
		failIf(start == escape, "escape character must be different than quotes");
		failIf(end == escape, "escape character must be different than quotes");
	}
	
}
