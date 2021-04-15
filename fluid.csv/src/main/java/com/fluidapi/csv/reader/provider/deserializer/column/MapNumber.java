package com.fluidapi.csv.reader.provider.deserializer.column;

import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;

public class MapNumber<T extends Number> extends MapSafe<T> {

	public MapNumber(CsvColumnMapper<T> safeMapper) {
		super(safeMapper);
	}

}
