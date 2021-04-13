package com.fluidapi.csv.reader.provider.linetocolumn;

import static java.util.regex.Pattern.compile;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.defaultIfEmpty;

import java.util.regex.Pattern;

import com.fluidapi.csv.reader.CsvLineToColumns;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SplitByDelimiter implements CsvLineToColumns {

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
