package com.fluidapi.csv.writer.provider.linejoiner;

import static com.fluidapi.csv.utility.CollectionUtils.contains;
import static com.fluidapi.csv.utility.IntPredicates.isNegative;
import static com.fluidapi.csv.validaton.FailCheck.failIf;
import static java.lang.Math.min;
import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.rightPad;
import static org.apache.commons.lang3.StringUtils.truncate;

import com.fluidapi.csv.writer.CsvColumnJoiner;

public class JoinFixedLength implements CsvColumnJoiner {
	
	final int[] widths;
	final int totalWidth;
	
	public JoinFixedLength(int...widths) {
		requireNonNull(widths, "widths");
		failIf(contains(widths, isNegative()), "negative width");
		
		this.widths = widths;
		this.totalWidth = stream(widths).sum();
	}
	
	@Override
	public String join(String[] columns) {
		
		// prepare for operation
		StringBuilder joined = new StringBuilder(totalWidth);
		final int length = min(widths.length, columns.length);
		
		// perform the operation per available column
		for (int index = 0; index < length; index++) {
			joined.append(fitWidth(columns[index], widths[index]));
		}
		
		// fill the rest of the line with space
		return rightPad(joined.toString(), totalWidth);
	}
	
	private String fitWidth(String content, int length) {
		if( content.length() < length ) return rightPad(content, length);
		if( content.length() > length ) return truncate(content, length);
		return content;
	}

}
