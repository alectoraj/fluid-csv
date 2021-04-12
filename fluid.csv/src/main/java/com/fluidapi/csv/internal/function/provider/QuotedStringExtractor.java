package com.fluidapi.csv.internal.function.provider;

import static com.fluidapi.csv.internal.function.provider.UnescapedIndexFinder.failResponse;

import java.util.function.Supplier;

import com.fluidapi.csv.exception.CsvFormatException;
import com.fluidapi.csv.internal.beans.Quotes;

public class QuotedStringExtractor implements Supplier<String> {
	
	protected final UnescapedIndexFinder indexFinder;
	protected final String data;
	protected final Quotes quote;
	
	public QuotedStringExtractor(String data, Quotes quote) {
		this.indexFinder = new UnescapedIndexFinder(data);
		this.data = data;
		this.quote = quote;
	}

	@Override
	public String get() {
		int quoteStarted = indexFinder.findNext(quote.quoteStart());
		int quoteEnded = indexFinder.findNext(quote.quoteEnd());

		// validate first
		if( quoteStarted == failResponse ) {
			return null;
		} else if( quoteEnded == failResponse ) {
			throw new CsvFormatException("quote started but never ended");
		} else if( quoteStarted == quoteEnded ) {
			throw new CsvFormatException("something's wrong, contact developer");
		} else {
			int delimiterAt = indexFinder.findNext(quote.delimiter());
			
			if( delimiterAt == failResponse && !indexFinder.isOutOfRange() ) {
				throw new CsvFormatException("delimiter expected but not found");
			}
		}
		
		return data.substring(++quoteStarted, quoteEnded);
	}

	
}
