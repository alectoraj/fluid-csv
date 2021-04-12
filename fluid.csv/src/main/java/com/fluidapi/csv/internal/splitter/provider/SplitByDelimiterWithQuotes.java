package com.fluidapi.csv.internal.splitter.provider;

import java.util.ArrayList;
import java.util.List;

import com.fluidapi.csv.LineSplitter;
import com.fluidapi.csv.internal.beans.Quotes;
import com.fluidapi.csv.internal.function.provider.QuotedStringExtractor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SplitByDelimiterWithQuotes implements LineSplitter {

	private final Quotes quotes;
	
	@Override
	public String[] apply(String line) {
		QuotedStringExtractor stringExtractor = new QuotedStringExtractor(line, quotes);
		List<String> columns = new ArrayList<>();
		
		String next;
		while( (next = stringExtractor.get()) != null ) {
			columns.add(next);
		}
		
		return columns.toArray(String[]::new);
	}
	
}
