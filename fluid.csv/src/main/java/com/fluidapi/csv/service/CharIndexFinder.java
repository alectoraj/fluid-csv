package com.fluidapi.csv.service;

import java.util.function.ToIntFunction;

/**
 * Finds the index for a given character.
 * <p>
 * <em>maintenance of states is provider specific</em>
 * </p>
 * 
 * @author Arindam Biswas
 * @since 1.0
 */
@FunctionalInterface
public interface CharIndexFinder extends ToIntFunction<Character> {
	
	/**
	 * if next index not found, this is the value that should be returned
	 */
	int failResponse = -1;

	int findNext(char character);
	
	@Override
	default int applyAsInt(Character value) {
		return findNext(value);
	}
}
