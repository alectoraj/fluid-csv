package com.fluidapi.csv.internal.function.provider;

import static java.util.Objects.requireNonNull;

import java.util.function.ToIntFunction;

public class UnescapedIndexFinder implements ToIntFunction<Character> {

	public static final char escape = '\\';
	public static final int failResponse = -1;
	
	private final char[] data;
	private char previous;
	private int index;
	
	public UnescapedIndexFinder(String string) {
		requireNonNull(string, "string");
		this.data = string.toCharArray();
		this.previous = '\0';
		this.index = -1;
	}

	@Override
	public int applyAsInt(Character value) {
		return findNext(value);
	}

	public int findNext(Character value) {

		requireNonNull(value, "character");
		if( isOutOfRange() ) return failResponse;
		
		findNextIndex(value);
		return isOutOfRange() ? failResponse : index;
		
	}
	
	public boolean isOutOfRange() {
		return index == data.length;
	}

	private void findNextIndex(char target) {
		while( ++index < data.length ) {
			char it = data[index];
			char last = previous;
			
			previous = it;
			if( it == target && last != escape ) {
				return;
			}
			
		}
	}

}
