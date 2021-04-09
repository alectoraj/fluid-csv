package com.fluidapi.csv.provider.lineinterpreter;

import static com.fluidapi.csv.validator.Requires.requiresTrue;
import static java.util.Objects.requireNonNull;

import com.fluidapi.csv.CsvLineInterpreter;
import com.fluidapi.csv.bean.CsvInterpretedLine;
import com.fluidapi.csv.function.provider.SafeStringExtractor;

public class SplitByFixedLength extends AbstractCsvLineInterpreter implements CsvLineInterpreter {
	
	protected final int[] lengths;
	
	public SplitByFixedLength(int...lengths) {
		requireNonNull(lengths, "lengths");
		requiresTrue(lengths.length != 0, "no length provided");
		
		this.lengths = lengths;
	}
	
	@Override
	public CsvInterpretedLine interpretSafe(String line) {
		return CsvInterpretedLine.of(splitLine(line));
	}

	private String[] splitLine(String line) {
		SafeStringExtractor stringExtractor = new SafeStringExtractor(line);
		String[] values = new String[lengths.length];
		
		int fromIndex = 0;
		int toIndex;
		for (int index = 0; index < lengths.length; index++) {
			toIndex = fromIndex + lengths[index];	// we'll have to it anyway, why do it twice then?
			
			values[index] = stringExtractor.slice(fromIndex, toIndex);
			fromIndex = toIndex;
		}
		
		return values;
	}

}
