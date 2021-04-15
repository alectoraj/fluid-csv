package com.fluidapi.csv.reader.provider.deserializer;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Stream;

import com.fluidapi.csv.exception.CsvException;
import com.fluidapi.csv.reader.AutoSetter;
import com.fluidapi.csv.reader.CsvBeanDeserializer;
import com.fluidapi.csv.reader.provider.bean.ConstructorInfo;
import com.fluidapi.csv.reader.provider.bean.CsvClassInfo;
import com.fluidapi.csv.reader.provider.bean.FieldInfo;
import com.fluidapi.csv.reader.provider.bean.SetterInfo;

public class AutoBeanDeserializer<T> implements CsvBeanDeserializer<T> {

	private final Function<String[], T> constructor;
	private final BiConsumer<T, String[]> populate;
	
	public AutoBeanDeserializer(Class<T> type) {
		// try setting type as accessible
		// find constructor, and try setting it accessible
		// find setters, try setting them as accessible
		// if proper has no accessible setter, try using assignment
		
		constructor = new BeanConstructor<>(type);
		populate = new BeanFieldUpdater<>(type);
	}
	
	@Override
	public T convert(String[] columns) {
		
		// get and populate
		T instance = constructor.apply(columns);
		populate.accept(instance, columns);
		
	//	TODO add validation
		
		return instance;
	}
	
	// TODO support non-default constructors
	static class BeanConstructor<T> implements Function<String[], T> {
		
		private final ConstructorInfo<T> constructor;
		
		public BeanConstructor(Class<T> type) {
			constructor = new CsvClassInfo<>(type)
					.csvConstructors()
					.findFirst()
					.orElseThrow(() -> new CsvException("no suitable accessible constructor found"));
		}

		@Override
		public T apply(String[] t) {
			return constructor.construct();
		}
		
	}
	
	/**
	 * Could be a consumer, but changes may or may not be applicable to original reference
	 * @author Arindam Biswas
	 */
	static class BeanFieldUpdater<T> implements BiConsumer<T, String[]> {
		
		private final List<AutoSetter> setters;
		
		public BeanFieldUpdater(Class<T> type) {
			CsvClassInfo<T> classInfo = new CsvClassInfo<>(type);
			setters = findSetters(classInfo).toList();
		}

		private Stream<AutoSetter> findSetters(CsvClassInfo<T> classInfo) {
			return Stream.concat(findFieldSetters(classInfo), findMethodSetters(classInfo));
		}
		private Stream<AutoSetter> findFieldSetters(CsvClassInfo<T> classInfo) {
			return classInfo.csvFields().map(FieldInfo::getSetter);
		}
		private Stream<AutoSetter> findMethodSetters(CsvClassInfo<T> classInfo) {
			return classInfo.csvSetters().map(SetterInfo::new);
		}

		@Override
		public void accept(T instance, String[] columns) {
			setters.forEach(setter -> setter.autoSet(instance, columns));
		}
		
	}
	
}
