package com.fluidapi.csv.reader.provider.linesplitter;

import static java.util.regex.Pattern.compile;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.defaultIfEmpty;

import java.util.regex.Pattern;

import com.fluidapi.csv.reader.CsvLineSplitter;
import com.fluidapi.csv.reader.CsvReader;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Splits a line using the given delimiter pattern
 * 
 * @author Arindam Biswas
 * @since 0.1
 * 
 * @see CsvReader#delimiter(Pattern)
 * @see CsvReader#delimiter(String)
 */
@RequiredArgsConstructor
public class SplitByDelimiter implements CsvLineSplitter {

	/**
	 * compiled {@link Pattern} for faster per-line processing
	 */
	@NonNull
	private final Pattern pattern;
	
	public SplitByDelimiter(String regex) {
		this(compile(regex));
	}

	@Override
	public String[] split(String line) {
		return pattern.split(defaultIfEmpty(line, EMPTY));
	}
	
}
