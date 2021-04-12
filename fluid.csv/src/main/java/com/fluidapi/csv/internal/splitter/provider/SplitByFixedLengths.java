package com.fluidapi.csv.internal.splitter.provider;

import static com.fluidapi.csv.internal.validation.Requires.requireTrue;
import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;

import java.util.stream.Stream;

import com.fluidapi.csv.LineSplitter;
import com.fluidapi.csv.internal.function.provider.StringExtractor;

public class SplitByFixedLengths implements LineSplitter {
	
	private final int[] lengths;
	
	public SplitByFixedLengths(int...lengths) {
		requireNonNull(lengths, "lengths");
		requireTrue(lengths.length > 0, "no lengths");
		verifyAll(lengths);
		
		this.lengths = lengths;
	}
	
	@Override
	public String[] apply(String line) {
		return toExtracted(line)
				.toArray(String[]::new);
	}

	protected Stream<String> toExtracted(String line) {
		return stream(lengths)
				.mapToObj(StringExtractor.of(line));
	}

	protected static void verifyAll(int[] lengths) {
		stream(lengths)
		.filter(n -> n <= 0)
		.findAny()
		.ifPresent(n -> {
			throw new IllegalArgumentException("negative lengths");
		});
	}
}
