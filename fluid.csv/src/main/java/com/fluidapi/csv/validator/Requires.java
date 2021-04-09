package com.fluidapi.csv.validator;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Requires {
	
	public static void requiresTrue( boolean state, String message ) {
		if( !state ) {
			throw new IllegalArgumentException(message);
		}
	}
	
}
