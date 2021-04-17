package com.fluidapi.csv.writer;

import com.fluidapi.csv.bean.Quote;
import com.fluidapi.csv.reader.provider.deserializer.PickString;
import com.fluidapi.csv.writer.provider.decorator.Escape;
import com.fluidapi.csv.writer.provider.decorator.WrapQuotes;
import com.fluidapi.csv.writer.provider.linejoiner.JoinByDelimiter;
import com.fluidapi.csv.writer.provider.linejoiner.JoinFixedLength;

/**
 * Provides shorthand methods to all sorts of functionalities that this utility
 * provides related to writing a csv
 * 
 * @author Arindam Biswas
 * @since 2.0
 */
public class CsvWriter {
	
	// COLUMNS TO LINE MAPPER //

	/**
	 * Specifies the plain text that needs to be used as delimiter while converting
	 * columns to line
	 * 
	 * @param delimiter a simple plain text, usually ; or ,
	 * @return {@link CsvColumnJoiner} as specified
	 */
	public static CsvColumnJoiner delimiter(String delimiter) {
		return new JoinByDelimiter(delimiter);
	}
	
	/**
	 * The columns would be suffixed with adequate spaces to make each column of
	 * fixed said length. This will also cut off a value to fit within the said
	 * column length
	 * 
	 * @param widths zero or positive valued character lengths to specify per column
	 *               width. zero lengths can be utilized to adjust column index in
	 *               configuration, although discouraged from using
	 * @return {@link CsvColumnJoiner} as specified
	 */
	public static CsvColumnJoiner fixed(int...widths) {
		return new JoinFixedLength(widths);
	}
	
	/**
	 * From a set of columns, pick only one column and continue for the Csv writing.
	 * 
	 * @param index 0-based index of the intended column, would result in
	 *              {@code null} if index exceeds length
	 * @return {@link CsvColumnJoiner} as specified
	 */
	public static CsvColumnJoiner only(int index) {
		return new PickString(index);
	}
	
	// COLUMN DECORATOR //

	/**
	 * Encloses every column with given quote.
	 * 
	 * <h3 style="color: #ea3212">CAUTION</h3>
	 * <P>
	 * For performance & logical reasons, this decorator does not escape anything,
	 * even the quote marks. it assumes they're either already escaped, or there can
	 * be no character that needs escaping.
	 * </P>
	 * <p>
	 * if escaping is required, refer {@link Escape}
	 * </p>
	 * 
	 * @param quote enclose every column with it, preferably simple characters
	 * @return {@link CsvColumnDecorator} as specified
	 */
	public static CsvColumnDecorator enclose(char quote) {
		return enclose(quote, quote);
	}
	
	/**
	 * Encloses every column with given quote.
	 * 
	 * <h3 style="color: #ea3212">CAUTION</h3>
	 * <P>
	 * For performance & logical reasons, this decorator does not escape anything,
	 * even the quote marks. it assumes they're either already escaped, or there can
	 * be no character that needs escaping.
	 * </P>
	 * <p>
	 * if escaping is required, refer {@link Escape}
	 * </p>
	 * 
	 * @param startQuote start of quotation, preferably simple characters
	 * @param endQuote   end of quotation, preferably simple characters
	 * @return {@link CsvColumnDecorator} as specified
	 */
	public static CsvColumnDecorator enclose(char startQuote, char endQuote) {
		return new WrapQuotes(startQuote, endQuote);
	}
	
	/**
	 * Any character, quotes or other sensitive character that needs to be escaped
	 * are to be escaped using backslash \ character.
	 * 
	 * @param characters the characters that needs to be escaped
	 * @return {@link CsvColumnDecorator} as specified
	 * @see #escape(char, char...)
	 */
	public static CsvColumnDecorator escapeStandard(char...characters) {
		return escape(Quote.ESCAPE, characters);
	}
	
	/**
	 * Any character, quotes or other sensitive character that needs to be escaped
	 * are to be escaped using a given escape character.
	 * 
	 * @param escapeWith the escape character to be used
	 * @param characters the characters that needs to be escaped
	 * @return {@link CsvColumnDecorator} as specified
	 * @see #escape(char...)
	 */
	public static CsvColumnDecorator escape(char escapeWith, char...characters) {
		return new Escape(escapeWith, characters);
	}
	
	// BEAN TO COLUMN MAPPER //

	/**
	 * Converts the bean into a set of columns using the specified configuration in
	 * the bean
	 * 
	 * @param <T>  any bean type
	 * @param type {@link Class} information of the bean
	 * @return {@link CsvBeanSerializer} as specified
	 */
	public static <T> CsvBeanSerializer<T> auto(Class<T> type) {
		return null;
	}
	
}
