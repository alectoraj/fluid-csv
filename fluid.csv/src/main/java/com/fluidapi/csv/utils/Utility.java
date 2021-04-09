package com.fluidapi.csv.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Utility {

	public static boolean between( int min, int it, int max ) {
		return min <= it && it <= max;
	}
	
}
