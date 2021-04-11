package com.fluidapi.csv.internal.function.provider;

import static java.lang.Math.min;
import static java.util.Objects.requireNonNull;
import static lombok.AccessLevel.PROTECTED;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.function.IntFunction;

import lombok.AllArgsConstructor;

@AllArgsConstructor(access = PROTECTED)
public class StringExtractor implements IntFunction<String> {
	
	private final String string;
	private int index;
	private boolean noMore;

	@Override
	public String apply(int length) {
		if( noMore || noLength(length) ) return EMPTY;
		
		int toIndex = min(index + length, string.length());
		int fromIndex = index;
		index(toIndex);
		
		return string.substring(fromIndex, toIndex);
	}

	private void index(int index) {
		this.index = index;
		noMore = (index == string.length());
	}
	
	private boolean noLength(int length) {
		return length <= 0;
	}

	public static StringExtractor of(String string) {
		requireNonNull(string, "string");
		return new StringExtractor(string, 0, false);
	}

}
