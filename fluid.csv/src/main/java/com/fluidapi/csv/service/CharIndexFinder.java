package com.fluidapi.csv.service;

import java.util.function.ToIntFunction;

@FunctionalInterface
public interface CharIndexFinder extends ToIntFunction<Character> {
	
	int failResponse = -1;

	int findNext(char character);
	
	@Override
	default int applyAsInt(Character value) {
		return findNext(value);
	}
}
