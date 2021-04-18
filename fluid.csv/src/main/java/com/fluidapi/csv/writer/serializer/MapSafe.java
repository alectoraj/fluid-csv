package com.fluidapi.csv.writer.serializer;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class MapSafe<T> implements CsvBeanMapper<T> {
	
	@Override
	public String apply(T t) {
		return t == null ? EMPTY : mapSafe(t);
	}
	
	protected String mapSafe(T t) {
		return t.toString();
	}

}
