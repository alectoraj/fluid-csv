package com.fluidapi.csv.writer.provider.decorator;

import com.fluidapi.csv.writer.CsvColumnDecorator;

public abstract class DecorateIndependently implements CsvColumnDecorator {

	@Override
	public final String[] decorate(String[] columns) {
		
		for (int index = 0; index < columns.length; index++) {
			columns[index] = decorate(columns[index]);
		}
		
		return columns;
	}
	
	protected abstract String decorate(String column);

}
