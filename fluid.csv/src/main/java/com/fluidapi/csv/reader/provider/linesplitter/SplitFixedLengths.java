package com.fluidapi.csv.reader.provider.linesplitter;

import static com.fluidapi.csv.utility.CollectionUtils.contains;
import static com.fluidapi.csv.utility.IntPredicates.isNegative;
import static com.fluidapi.csv.validaton.FailCheck.failIf;
import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;

import com.fluidapi.csv.reader.CsvLineSplitter;
import com.fluidapi.csv.reader.CsvReader;
import com.fluidapi.csv.service.provider.StringExtractor;

/**
 * To setup, provide each column length in ltr sequence.
 * <p>
 * This, however, does not deal with the spaces used to form the fixed width for
 * variable contents since it assumes the spaces maybe relevant piece of
 * information.<br/>
 * In order to remove the spaces, use {@link CsvReader#strip()} or
 * {@link CsvReader#trim()}
 * </p>
 * <p>
 * Zero lengths are discouraged, but maybe used to adjust the column index in
 * Bean when the index configuration in bean is rigid.
 * </p>
 * 
 * @author Arindam Biswas
 * @since 0.1
 * 
 * @see CsvReader#fixed(int...)
 */
public class SplitFixedLengths implements CsvLineSplitter {

	/**
	 * non-negative lengths in ltr sequence
	 */
	private final int[] lengths;

	public SplitFixedLengths(int... lengths) {
		requireNonNull(lengths, "lengths");
		failIf(isEmpty(lengths), "no length provided");
		failIf(contains(lengths, isNegative()), "negative lengths");

		this.lengths = lengths;
	}

	@Override
	public String[] split(String line) {
		return stream(lengths).mapToObj(new StringExtractor(line)::next).toArray(String[]::new);
	}

}
