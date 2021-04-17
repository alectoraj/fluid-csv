package com.fluidapi.csv.reader.provider.deserializer.column.preprocessor;

import static java.util.Optional.of;

import java.util.function.UnaryOperator;

import com.fluidapi.csv.annotations.CsvStrip;
import com.fluidapi.csv.annotations.CsvToLowerCase;
import com.fluidapi.csv.annotations.CsvToUpperCase;
import com.fluidapi.csv.annotations.CsvTrim;
import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;
import com.fluidapi.csv.reader.provider.bean.AnnotatedInfo;
import com.fluidapi.csv.reader.provider.bean.TypeInfo;

public class MapPreprocessor {
	
	public static CsvColumnMapper<String> findSupported(TypeInfo<?> typeInfo, AnnotatedInfo<?> origin) {
		
		return of(origin)
				.map(MapPreprocessor::toSafeDecorative)
				.map(SafeDecorateAs::new)
				.orElse(null);
	}

	private static UnaryOperator<String> toSafeDecorative(AnnotatedInfo<?> origin) {
		
		UnaryOperator<String> safeDecorative = toTrimming(origin);
		safeDecorative = join(safeDecorative, toCasing(origin));
		
		return safeDecorative;
	}

	private static UnaryOperator<String> toCasing(AnnotatedInfo<?> origin) {
		if( origin.hasAnnotation(CsvToUpperCase.class) ) return String::toUpperCase;
		if( origin.hasAnnotation(CsvToLowerCase.class) ) return String::toLowerCase;
		
		return null;
	}

	private static UnaryOperator<String> toTrimming(AnnotatedInfo<?> origin) {
		if( origin.hasAnnotation(CsvStrip.class) ) return String::strip;
		if( origin.hasAnnotation(CsvTrim.class) ) return String::trim;
		
		return null;
	}
	
	private static UnaryOperator<String> join(UnaryOperator<String> before, UnaryOperator<String> after) {
		if( before == null ) return after;
		if( after == null ) return before;
		return (String column) -> after.apply(before.apply(column));
	}
	
}
