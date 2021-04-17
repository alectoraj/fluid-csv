package com.fluidapi.csv.writer.provider.serializer;

import java.util.List;

import com.fluidapi.csv.writer.CsvBeanSerializer;
import com.fluidapi.csv.writer.serializer.CsvBeanMapper;

public class AutoBeanSerializer<T> implements CsvBeanSerializer<T> {
	
	final List<CsvBeanMapper<T>> extractors;

	public AutoBeanSerializer(Class<T> type) {
		this.extractors = null;
	}

	@Override
	public String[] convert(T t) {
		// TODO Auto-generated method stub
		return null;
	}

}
