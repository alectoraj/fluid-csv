package com.fluidapi.csv.utility;

import static com.fluidapi.csv.utility.StreamUtils.fastStream;

import java.util.function.IntPredicate;

public interface CollectionUtils {
	
	static boolean contains(int[] ints, IntPredicate condition) {
		return fastStream(ints).anyMatch(condition);
	}
	
}
