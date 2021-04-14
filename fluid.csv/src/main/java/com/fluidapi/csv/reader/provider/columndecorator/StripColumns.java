package com.fluidapi.csv.reader.provider.columndecorator;

import com.fluidapi.csv.reader.CsvColumnDecorator;

/**
 * Removes white spaces from around a column using {@link String#strip()}
 * 
 * @author Arindam Biswas
 * @since 1.0
 * @see TrimColumns
 */
public class StripColumns extends AbstractSafeColumnDecorator implements CsvColumnDecorator {

	@Override
	protected String decorateSafe(String column) {
		return column.strip();
	}

}
