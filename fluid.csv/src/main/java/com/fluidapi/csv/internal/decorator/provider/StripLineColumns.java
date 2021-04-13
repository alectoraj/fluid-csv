package com.fluidapi.csv.internal.decorator.provider;

import com.fluidapi.csv.LineDecorator;

/**
 * Strips each string in line in a mutable fashion
 * 
 * @author Arindam Biswas
 */
public class StripLineColumns implements LineDecorator {

	@Override
	public String[] decorate(String[] columns) {
		for (int index = 0; index < columns.length; index++) {
			columns[index] = columns[index].strip();
		}
		
		return columns;
	}

}
