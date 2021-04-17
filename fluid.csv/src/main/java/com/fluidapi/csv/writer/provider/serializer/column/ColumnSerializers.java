package com.fluidapi.csv.writer.provider.serializer.column;

import com.fluidapi.csv.provider.bean.AnnotatedInfo;
import com.fluidapi.csv.provider.bean.ClassInfo;
import com.fluidapi.csv.provider.bean.TypeInfo;
import com.fluidapi.csv.writer.serializer.CsvBeanMapper;

public class ColumnSerializers {
	
	/**
	 * @param typeInfo to refer data type
	 * @param property to find annotations
	 * @return if a custom serializer is mentioned, or a default one | either way
	 *         with preprocessors
	 */
	public static CsvBeanMapper<?> of(Class<?> typeInfo, AnnotatedInfo<?> property) {
		return of(new ClassInfo<>(typeInfo), property);
	}
	
	/**
	 * @param typeInfo to refer data type
	 * @param property to find annotations
	 * @return if a custom serializer is mentioned, or a default one | either way
	 *         with preprocessors
	 */
	public static CsvBeanMapper<?> of(TypeInfo<?> typeInfo, AnnotatedInfo<?> property) {
		return null;
	}
	
}
