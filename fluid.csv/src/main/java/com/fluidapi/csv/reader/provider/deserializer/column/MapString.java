package com.fluidapi.csv.reader.provider.deserializer.column;

import com.fluidapi.csv.function.MapConstructor;
import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;
import com.fluidapi.csv.utility.MapSupport;

public class MapString implements CsvColumnMapper<String> {
	
	@Override
	public String map(String column) {
		return column;
	}

	public static final MapSupport<MapString> support = new MapSupport<>();
	
	static {
		MapConstructor<MapString> constructMe = (t, u) -> new MapString();
		support.register(String.class, constructMe);
		support.register(CharSequence.class, constructMe);
	}
}
