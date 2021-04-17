package com.fluidapi.csv.reader.provider.deserializer.column;

import static com.fluidapi.csv.validaton.FailCheck.failIf;

import java.util.stream.Stream;

import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;
import com.fluidapi.csv.reader.provider.bean.AnnotatedInfo;
import com.fluidapi.csv.reader.provider.bean.TypeInfo;
import com.fluidapi.csv.reader.provider.deserializer.column.datetime.MapOldTemporal;
import com.fluidapi.csv.reader.provider.deserializer.column.enums.MapEnum;
import com.fluidapi.csv.reader.provider.deserializer.column.number.MapNumber;
import com.fluidapi.csv.reader.provider.deserializer.column.preprocessor.MapPreprocessor;
import com.fluidapi.csv.reader.provider.deserializer.column.primitive.MapPrimitive;
import com.fluidapi.csv.reader.provider.deserializer.column.temporal.MapTemporal;
import com.fluidapi.csv.reader.provider.deserializer.column.wrapper.MapWrapper;
import com.fluidapi.csv.utility.FunctionUtils;

public class ColumnDeserializers {
	
	public static CsvColumnMapper<?> of(TypeInfo<?> typeInfo, AnnotatedInfo<?> origin) {
		
		return of(typeInfo, origin, findSupportOf(typeInfo, origin));
	}
	
	public static CsvColumnMapper<?> of(TypeInfo<?> typeInfo, AnnotatedInfo<?> origin, CsvColumnMapper<?> mapper) {
		
		// find preprocessor
		CsvColumnMapper<String> preprocessor = MapPreprocessor.findSupported(typeInfo, origin);

		// join prefix mapper with field mapper
		return FunctionUtils.chain(preprocessor,
				mapper != null ? mapper : findApiProvided(typeInfo, origin));
	}
	
	private static CsvColumnMapper<?> findApiProvided(TypeInfo<?> typeInfo, AnnotatedInfo<?> origin) {
		
		// find a default one
		CsvColumnMapper<?> mapper = findSupportOf(typeInfo, origin);
		
		// check if anything default could be found
		failIf(mapper == null,
				"no mapper supported for %s, try using @CsvDeserialzier"
				.formatted(typeInfo.getType()),
				UnsupportedOperationException::new);
		
		// given one could be found, return it
		return mapper;
	}
	
	private static CsvColumnMapper<?> findSupportOf(TypeInfo<?> typeInfo, AnnotatedInfo<?> origin) {
		
		// because we know MapSupport uses Class<?> info as key,
		// let's keep it handy to reduce at least 1 set of redundant call stack
		Class<?> type = typeInfo.getType();
		
		return Stream.of(
			//	support maps in preference order
			//	i.e. first one supporting the type is taken
				MapString.support,
				MapPrimitive.support,
				MapNumber.support,
				MapWrapper.support,
				MapTemporal.support,
				MapOldTemporal.support,
				MapEnum.support
		)
		
		// find the supported, use it to get the mapper and return
		.filter( support -> support.supports(type) )
		.map( support -> support.of(typeInfo, origin) )
		.findFirst()
		.orElse(null);
		
	}
	
}
