package com.fluidapi.csv.writer;

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
		return null;
	}
	
	/**
	 * The columns would be suffixed with adequate spaces to make each column of
	 * fixed said length. This will also cut off a value to fit within the said
	 * column length
	 * 
	 * @param lengths zero or positive valued character lengths to specify per
	 *                column width. zero lengths can be utilized to adjust column
	 *                index in configuration, although discouraged from using
	 * @return {@link CsvColumnJoiner} as specified
	 */
	public static CsvColumnJoiner fixed(int...lengths) {
		return null;
	}
	
	/**
	 * From a set of columns, pick only one column and continue for the Csv writing.
	 * 
	 * @param index 0-based index of the intended column, would result in
	 *              {@code null} if index exceeds length
	 * @return {@link CsvColumnJoiner} as specified
	 */
	public static CsvColumnJoiner only(int index) {
		return null;
	}
	
	// COLUMN DECORATOR //

	/**
	 * Encloses every column with given quote
	 * 
	 * @param quote enclose every column with it, preferably plain text
	 * @return {@link CsvColumnDecorator} as specified
	 */
	public static CsvColumnDecorator enclose(String quote) {
		return null;
	}
	
	/**
	 * Encloses every column with given quote
	 * 
	 * @param startQuote start of quotation, preferably plain text
	 * @param endQuote end of quotation, preferably plain text
	 * @return {@link CsvColumnDecorator} as specified
	 */
	public static CsvColumnDecorator enclose(String startQuote, String endQuote) {
		return null;
	}
	
	/**
	 * Any character, quotes or other sensitive character that needs to be escaped
	 * are to be escaped using backslash \ character.
	 * 
	 * @param characters the characters that needs to be escaped
	 * @return {@link CsvColumnDecorator} as specified
	 * @see #escape(char, char...)
	 */
	public static CsvColumnDecorator escape(char...characters) {
		return null;
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
		return null;
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
