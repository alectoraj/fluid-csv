package com.fluidapi.csv.reader.provider.deserializer.column;

import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;
import com.fluidapi.csv.utility.MapSupport;

public class MapString implements CsvColumnMapper<String> {
	
	@Override
	public String map(String column) {
		return column;
	}

	public static final MapSupport<MapString> support = new MapSupport<>();
	
	static {
		support.register((t, u) -> new MapString(), String.class, CharSequence.class);
	}
}
