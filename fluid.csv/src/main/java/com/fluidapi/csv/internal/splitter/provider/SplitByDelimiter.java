package com.fluidapi.csv.internal.splitter.provider;

import static com.fluidapi.csv.internal.validation.Requires.requireTrue;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import com.fluidapi.csv.LineSplitter;

public class SplitByDelimiter implements LineSplitter {
	
	private final String delimiter;
	
	public SplitByDelimiter(String regex) {
		requireNonNull(regex, "delimiter regex");
		requireTrue(isNotEmpty(regex), "delimiter regex");
		
		delimiter = regex;
	}

	@Override
	public String[] apply(String line) {
		return line.split(delimiter);
	}
}
