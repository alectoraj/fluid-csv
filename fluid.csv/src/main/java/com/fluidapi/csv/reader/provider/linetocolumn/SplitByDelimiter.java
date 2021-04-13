package com.fluidapi.csv.reader.provider.linetocolumn;

import static java.util.regex.Pattern.compile;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.defaultIfEmpty;

import java.util.regex.Pattern;

import com.fluidapi.csv.reader.CsvLineToColumns;
import com.fluidapi.csv.reader.CsvReader;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Splits a line using the given delimiter pattern
 * 
 * @author Arindam Biswas
 * @since 1.0
 * 
 * @see CsvReader#delimiter(Pattern)
 * @see CsvReader#delimiter(String)
 */
@RequiredArgsConstructor
public class SplitByDelimiter implements CsvLineToColumns {

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
