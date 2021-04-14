package com.fluidapi.csv.reader.provider.columndecorator;

import com.fluidapi.csv.reader.CsvColumnDecorator;

/**
 * Removes white spaces from around a column using {@link String#trim()}
 * 
 * @author Arindam Biswas
 * @since 1.0
 * @see StripColumns
 */
public class TrimColumns extends AbstractSafeColumnDecorator implements CsvColumnDecorator {

	@Override
	protected String decorateSafe(String column) {
		return column.trim();
	}

}
