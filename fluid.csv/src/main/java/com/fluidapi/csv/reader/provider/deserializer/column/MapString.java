package com.fluidapi.csv.reader.provider.deserializer.column;

import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;

public class MapString implements CsvColumnMapper<String> {
	
	@Override
	public String map(String column) {
		return column;
	}

}
