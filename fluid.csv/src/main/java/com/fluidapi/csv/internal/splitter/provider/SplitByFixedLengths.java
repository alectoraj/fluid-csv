package com.fluidapi.csv.internal.splitter.provider;

import static com.fluidapi.csv.internal.validation.Requires.requireTrue;
import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;
import static lombok.AccessLevel.PROTECTED;

import com.fluidapi.csv.LineSplitter;
import com.fluidapi.csv.internal.function.provider.StringExtractor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = PROTECTED)
public class SplitByFixedLengths implements LineSplitter {
	
	private final int[] lengths;
	
	@Override
	public String[] apply(String line) {
		return stream(lengths)
				.mapToObj(StringExtractor.of(line))
				.toArray(String[]::new);
	}

	public static LineSplitter using(int...lengths) {
		requireNonNull(lengths, "lengths");
		requireTrue(lengths.length > 0, "no lengths");
		verifyAll(lengths);
		
		return new SplitByFixedLengths(lengths);
	}

	private static void verifyAll(int[] lengths) {
		stream(lengths)
		.filter(n -> n <= 0)
		.findAny()
		.ifPresent(n -> {
			throw new IllegalArgumentException("negative lengths");
		});
	}
}
