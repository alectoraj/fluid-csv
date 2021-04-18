package com.fluidapi.csv.reader.provider.columndecorator;

import com.fluidapi.csv.reader.CsvColumnDecorator;

/**
 * Removes white spaces from around a column using {@link String#strip()}
 * 
 * @author Arindam Biswas
 * @since 0.1
 * @see TrimColumns
 */
public class StripColumns extends SafeColumnDecorator implements CsvColumnDecorator {

	@Override
	protected String decorateSafe(String column) {
		return column.strip();
	}

}
