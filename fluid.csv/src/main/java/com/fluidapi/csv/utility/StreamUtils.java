package com.fluidapi.csv.utility;

import static java.util.Arrays.stream;

import java.util.stream.IntStream;

public interface StreamUtils {
	
	static IntStream fastStream(IntStream stream, int estimatedLength) {
		return estimatedLength > 64 ? stream.parallel() : stream;
	}
	
	static IntStream fastStream(int...ints) {
		return fastStream(stream(ints), ints.length);
	}
}
