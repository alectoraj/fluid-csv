package com.fluidapi.csv.reader.provider.deserializer;

import java.util.function.BiConsumer;
import java.util.function.Function;

import com.fluidapi.csv.exception.CsvException;
import com.fluidapi.csv.reader.CsvBeanDeserializer;
import com.fluidapi.csv.reader.provider.bean.ConstructorInfo;
import com.fluidapi.csv.reader.provider.bean.CsvClassInfo;

public class AutoBeanDeserializer<T> implements CsvBeanDeserializer<T> {

	private final Function<String[], T> constructor;
	private final BiConsumer<T, String[]> populate;
	
	public AutoBeanDeserializer(Class<T> type) {
		// try setting type as accessible
		// find constructor, and try setting it accessible
		// find setters, try setting them as accessible
		// if proper has no accessible setter, try using assignment
		
		// TODO map proper value
		constructor = null;
		populate = null;
	}
	
	@Override
	public T convert(String[] columns) {
		
		// get and populate
		T instance = constructor.apply(columns);
		populate.accept(instance, columns);
		
	//	TODO add validation
		
		return instance;
	}
	
	static class BeanConstructor<T> implements Function<String[], T> {
		
		private final ConstructorInfo constructor;
		
		public BeanConstructor(Class<T> type) {
			constructor = new CsvClassInfo<>(type)
					.csvConstructors()
					.findFirst()
					.orElseThrow(() -> new CsvException("no suitable accessible constructor found"));
		}

		@Override
		public T apply(String[] t) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

}
