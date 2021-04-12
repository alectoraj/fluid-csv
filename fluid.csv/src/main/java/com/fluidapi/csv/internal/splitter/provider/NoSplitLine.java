package com.fluidapi.csv.internal.splitter.provider;

import com.fluidapi.csv.LineSplitter;

public class NoSplitLine implements LineSplitter {
	
	@Override
	public String[] apply(String line) {
		return new String[] { line };
	}
}
