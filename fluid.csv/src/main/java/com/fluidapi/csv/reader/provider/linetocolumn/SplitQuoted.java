package com.fluidapi.csv.reader.provider.linetocolumn;

import static com.fluidapi.csv.service.CharIndexFinder.failResponse;
import static com.fluidapi.csv.validaton.Requires.failIf;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.fluidapi.csv.bean.Quote;
import com.fluidapi.csv.exception.CsvFormatException;
import com.fluidapi.csv.reader.CsvLineToColumns;
import com.fluidapi.csv.service.CharIndexFinder;
import com.fluidapi.csv.service.provider.UnescapedCharIndexFinder;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SplitQuoted implements CsvLineToColumns {
	
	@NonNull
	private final Quote quote;

	@Override
	public String[] split(String line) {
		return Stream.generate(findNext(line))
				.takeWhile(Objects::nonNull)
				.toArray(String[]::new);
	}
	
	public Supplier<String> findNext(String line) {
		CharIndexFinder index = new UnescapedCharIndexFinder(line, quote.escape());
		return () -> {
			
			// find & validate if another occurrence of quote-start is there
			int start = index.findNext(quote.start());
			if( start == failResponse ) {
				return null;
			}
			
			// find & validate if end quote is found
			int end = index.findNext(quote.end());
			failIf(end == failResponse, "quote didn't end", CsvFormatException::new);
			
			return line.substring(start + 1, end);
		};
	}

}
