package com.fluidapi.csv.internal.splitter.provider;

import java.util.stream.Stream;

public class SplitByFixedLengthsThenStrip extends SplitByFixedLengths {

	public SplitByFixedLengthsThenStrip(int...lengths) {
		super(lengths);
	}
	
	@Override
	protected Stream<String> toExtracted(String line) {
		return super.toExtracted(line)
					.map(String::strip);
	}

}
