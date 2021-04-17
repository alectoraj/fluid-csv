package com.fluidapi.csv.writer.provider.serializer;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import com.fluidapi.csv.provider.bean.AnnotatedInfo;
import com.fluidapi.csv.provider.bean.AutoGetter;
import com.fluidapi.csv.provider.bean.CsvClassInfo;
import com.fluidapi.csv.writer.CsvBeanSerializer;
import com.fluidapi.csv.writer.serializer.CsvBeanMapper;

public class AutoBeanSerializer<T> implements CsvBeanSerializer<T> {
	
	final List<AutoGetter> extractors;

	public AutoBeanSerializer(Class<T> type) {
		this.extractors = listExtractors(type);
	}

	@Override
	public String[] convert(T t) {
		return extractors.stream()
				.map(mapper -> mapper.apply(t))
				.toArray(String[]::new);
	}
	
	private List<AutoGetter> listExtractors(Class<T> type) {
		CsvClassInfo<T> classInfo = new CsvClassInfo<>(type);

		Stream<GetOperation> fieldGetters = findFieldGetters(classInfo);
		Stream<GetOperation> methodGetters = findMethodGetters(classInfo);
		
		return Stream.concat(fieldGetters, methodGetters)
				.sorted(Comparator.comparingInt(GetOperation::colIndex))
				.map(GetOperation::auto)
				.toList();
	}

	// extract "get" operation
	// transform it using column serializer or CsvBeamMapper<T>
	// the combination of both would be the auto-getter of that type for that field/method
	
	private Stream<GetOperation> findFieldGetters(CsvClassInfo<T> classInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	private Stream<GetOperation> findMethodGetters(CsvClassInfo<T> classInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// TODO first find fieldGetters & methodGetters
	// join them through GetterInfo
	// and then convert them to GetOperation

	private static record GetOperation (
			Class<?> typeInfo,
			AnnotatedInfo<?> property,
			Function<?, ?> toField,
			CsvBeanMapper<?> fieldToString ) {
		
		AutoGetter auto() {
			return null;
		}
		
		int colIndex() {
			return property.getCsvColumnIndex();
		}
		
	}

}
