package com.fluidapi.csv.reader.provider.linetocolumn;

import static com.fluidapi.csv.utility.CollectionUtils.contains;
import static com.fluidapi.csv.utility.IntPredicates.isZeroOrPositive;
import static com.fluidapi.csv.validaton.Requires.failIf;
import static com.fluidapi.csv.validaton.Requires.requireTrue;
import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;

import com.fluidapi.csv.reader.CsvLineToColumns;
import com.fluidapi.csv.service.provider.StringExtractor;

public class SplitFixedLengths implements CsvLineToColumns {
	
	private final int[] lengths;
	
	public SplitFixedLengths(int...lengths) {
		requireNonNull(lengths, "lengths not provided");
		failIf(isEmpty(lengths), "no length provided");
		requireTrue(contains(lengths, isZeroOrPositive()), "negative lengths");
		
		this.lengths = lengths;
	}

	@Override
	public String[] split(String line) {
		return stream(lengths)
				.mapToObj(new StringExtractor(line)::next)
				.toArray(String[]::new);
	}

}
