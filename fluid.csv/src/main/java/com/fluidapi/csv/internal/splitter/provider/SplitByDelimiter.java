package com.fluidapi.csv.internal.splitter.provider;

import static com.fluidapi.csv.internal.validation.Requires.requireTrue;
import static java.util.Objects.requireNonNull;
import static lombok.AccessLevel.PROTECTED;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import com.fluidapi.csv.LineSplitter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = PROTECTED)
public class SplitByDelimiter implements LineSplitter {
	
	private final String delimiter;

	@Override
	public String[] apply(String line) {
		return line.split(delimiter);
	}

	public static LineSplitter using(String regex) {
		requireNonNull(regex, "delimiter regex");
		requireTrue(isNotEmpty(regex), "delimiter regex");
		return new SplitByDelimiter(regex);
	}
}
