package com.fluidapi.csv.writer.provider.serializer.column;

import com.fluidapi.csv.annotations.CsvSerializer;
import com.fluidapi.csv.exception.CsvException;
import com.fluidapi.csv.provider.bean.AnnotatedInfo;
import com.fluidapi.csv.provider.bean.TypeInfo;
import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;
import com.fluidapi.csv.reader.provider.deserializer.column.preprocessor.MapPreprocessor;
import com.fluidapi.csv.writer.serializer.CsvBeanMapper;
import com.fluidapi.csv.writer.serializer.MapCalendar;
import com.fluidapi.csv.writer.serializer.MapDate;
import com.fluidapi.csv.writer.serializer.MapSafe;
import com.fluidapi.csv.writer.serializer.MapTemporal;

public class ColumnSerializers {
	
	/**
	 * @param type to refer data type
	 * @param property to find annotations
	 * @return if a custom serializer is mentioned, or a default one | either way
	 *         with preprocessors
	 */
	public static CsvBeanMapper<?> of(Class<?> type, AnnotatedInfo<?> property) {
		
		// find custom types
		if( property.hasAnnotation(CsvSerializer.class) ) return construct(property.findAnnotation(CsvSerializer.class));
		
		// find complex types
		if( MapTemporal.supports(type) ) return new MapTemporal(property);
		if( MapCalendar.supports(type) ) return new MapCalendar(property);
		if( MapDate.supports(type) ) return new MapDate(property);
		
		// find default types
		return new MapSafe<>();
	}

	/**
	 * @param typeInfo to refer data type
	 * @param property to find annotations
	 * @return if a custom serializer is mentioned, or a default one | either way
	 *         with preprocessors
	 */
	public static CsvBeanMapper<Object> of(TypeInfo<?> typeInfo, AnnotatedInfo<?> property) {
		
		// find bean mapper
		@SuppressWarnings("unchecked")
		CsvBeanMapper<Object> beanMapper = (CsvBeanMapper<Object>) of(typeInfo.getType(), property);

		// find preprocessor, and if present, combine
		CsvColumnMapper<String> postprocessor = MapPreprocessor.findSupported(typeInfo, property);
		return postprocessor != null
			 ? t -> postprocessor.apply(beanMapper.apply(t))
			 : beanMapper;
	}
	
	@SuppressWarnings("unchecked")
	private static CsvBeanMapper<Object> construct(CsvSerializer csvSerializer) {
		Class<?> type = csvSerializer.value();
		
		try {
			return (CsvBeanMapper<Object>) type.getConstructor().newInstance();
		} catch (Exception e) {
			throw new CsvException("error constructing serializer of type " + type, e);
		}
	}
	
}
