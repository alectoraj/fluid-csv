package com.fluidapi.csv.reader.provider.columndecorator;

import com.fluidapi.csv.reader.CsvColumnDecorator;
import com.fluidapi.csv.reader.CsvReader;
import com.fluidapi.csv.reader.provider.deserializer.column.preprocessor.SafeUnescape;

/**
 * Removes escape indicators
 * 
 * @author Arindam Biswas
 * @since 1.0
 * @see CsvReader#unescape()
 * @see CsvReader#unescape(char)
 * @see SafeUnescape
 */
public class Unescape extends SafeColumnDecorator implements CsvColumnDecorator {
	
	final SafeUnescape operator;

	public Unescape(char escapeIndicator) {
		operator = new SafeUnescape(escapeIndicator);
	}

	@Override
	protected String decorateSafe(String column) {
		return column.transform(operator);
	}

}
