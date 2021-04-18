package com.fluidapi.csv.reader.provider.columndecorator;

import static org.apache.commons.lang3.ArrayUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import com.fluidapi.csv.reader.CsvColumnDecorator;

/**
 * Creates a null-safe environment so child class can only decorate columns that
 * has content.
 * <p>
 * <em>Not suitable if decoration has special cases for null or empty
 * columns, or even null or empty set of columns</em>
 * </p>
 * 
 * @author Arindam Biswas
 * @since 0.1
 */
public abstract class SafeColumnDecorator implements CsvColumnDecorator {

	@Override
	public final String[] decorate(String[] columns) {
		if( isNotEmpty(columns) ) {
			decorateColumns(columns);
		}
		
		return columns;
	}

	/**
	 * given there's at least one column, decorate them
	 * 
	 * @param columns non-empty array of columns
	 */
	private void decorateColumns(String[] columns) {
		for (int index = 0; index < columns.length; index++) {
			if( !isEmpty(columns[index]) ) {
				columns[index] = decorateSafe(columns[index]);
			}
		}
	}

	/**
	 * decorate without having to worry about column being null.
	 * 
	 * @param column a string that is definitely neither {@code null} nor empty
	 * @return decorated string, may very well be {@code null} or empty
	 */
	protected abstract String decorateSafe(String column);

}
