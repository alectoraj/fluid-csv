package com.fluidapi.csv.internal.splitter.provider;

import static lombok.AccessLevel.PROTECTED;

import com.fluidapi.csv.LineSplitter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = PROTECTED)
public class NoSplitLine implements LineSplitter {
	
	@Override
	public String[] apply(String line) {
		return new String[] { line };
	}

	public static LineSplitter get() {
		return new NoSplitLine();
	}
}
