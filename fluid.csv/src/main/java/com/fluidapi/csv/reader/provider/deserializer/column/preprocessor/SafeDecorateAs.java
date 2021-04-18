package com.fluidapi.csv.reader.provider.deserializer.column.preprocessor;

import java.util.function.UnaryOperator;

import com.fluidapi.csv.reader.provider.deserializer.column.MapSafe;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SafeDecorateAs extends MapSafe<String> {
	
	@NonNull
	protected final UnaryOperator<String> decorator;

	@Override
	protected String mapSafe(String column) {
		return column.transform(decorator);
	}
	
	@Override
	protected String defaultIfEmpty(String column) {
		return column;
	}

}
