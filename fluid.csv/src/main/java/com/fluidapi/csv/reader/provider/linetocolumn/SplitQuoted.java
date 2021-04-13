package com.fluidapi.csv.reader.provider.linetocolumn;

import static com.fluidapi.csv.service.CharIndexFinder.failResponse;
import static com.fluidapi.csv.validaton.FailCheck.failIf;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.fluidapi.csv.bean.Quote;
import com.fluidapi.csv.exception.CsvFormatException;
import com.fluidapi.csv.reader.CsvLineToColumns;
import com.fluidapi.csv.reader.CsvReader;
import com.fluidapi.csv.service.CharIndexFinder;
import com.fluidapi.csv.service.provider.UnescapedCharIndexFinder;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * reads through the line to consider text enclosed by the quotes/enclosing pair
 * of characters as each column. there may or may not be any delimiter between
 * quoted parts, but they're entirely ignored. Also, any text before the first
 * quote or after the last quote are also ignored. However, missing quote would
 * result in {@link CsvFormatException}
 * 
 * @author Arindam Biswas
 * @since 1.0
 * 
 * @see CsvReader#dequote(Quote)
 * @see CsvReader#dequote(char)
 * @see CsvReader#dequote(char, char)
 * @see CsvReader#dequote(char, char, char)
 */
@RequiredArgsConstructor
public class SplitQuoted implements CsvLineToColumns {
	
	/**
	 * property validation of quote is done by {@link Quote} class itself.
	 * no further validation is required, except for checking it is not null.
	 */
	@NonNull
	private final Quote quote;

	@Override
	public String[] split(String line) {
		return Stream.generate(findNext(line))
				.takeWhile(Objects::nonNull)
				.toArray(String[]::new);
	}
	
	/**
	 * returns a supplier, that, with each call to {@link Supplier#get()} would
	 * return the next found column enclosed by defined quotes.
	 * <p>
	 * It would also try to validate if there's a quote mismatch in the line.
	 * </p>
	 * 
	 * @param line a whole line to extract from
	 * @return a supplier that would return columns in ltr order with each call to
	 *         {@link Supplier#get()}
	 */
	protected Supplier<String> findNext(String line) {
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
			
			// extract next string, and validate if it contains start of quote
			String next = line.substring(start + 1, end);
			verifyColumn(next);
			
			return next;
		};
	}

	/**
	 * checks if next found column contains any unescaped start quote
	 * 
	 * @param column the next possible value for next column
	 */
	protected void verifyColumn(String column) {
		if( !isEmpty(column) ) {
			CharIndexFinder verifier = new UnescapedCharIndexFinder(column, quote.escape());
			failIf(verifier.findNext(quote.start()) == failResponse,
					"Quote restarted before ending the existing one",
					CsvFormatException::new);
		}
	}

}
