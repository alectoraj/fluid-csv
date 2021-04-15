package com.fluidapi.csv.reader.provider.deserializer.column;

import java.math.BigDecimal;

import com.fluidapi.csv.annotations.CsvStrip;
import com.fluidapi.csv.annotations.CsvTrim;
import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;
import com.fluidapi.csv.reader.provider.bean.AnnotatedInfo;
import com.fluidapi.csv.reader.provider.bean.TypeInfo;

public class ColumnMappers {
	
	public static CsvColumnMapper<?> of(TypeInfo<?> typeInfo, AnnotatedInfo<?> origin) {
		
		CsvColumnMapper<String> trimming = findTrimming(origin);
		
		if( typeInfo.isOfType(String.class) ) return trimmerOrString(trimming);
		if( typeInfo.isOfType(Integer.class, int.class) ) return join(trimming, new MapInteger());
		if( typeInfo.isOfType(BigDecimal.class) ) return join(trimming, new MapBigDecimal());
		
		return null;
	}

	private static CsvColumnMapper<?> trimmerOrString(CsvColumnMapper<?> trimming) {
		return trimming != null ? trimming : new MapString();
	}

	private static CsvColumnMapper<String> findTrimming(AnnotatedInfo<?> origin) {
		if( origin.hasAnnotation(CsvStrip.class) ) return new MapStringStripped();
		if( origin.hasAnnotation(CsvTrim.class) ) return new MapStringTrimmed();
		return null;
	}
	
	private static CsvColumnMapper<?> join(CsvColumnMapper<String> before, CsvColumnMapper<?> after) {
		if( before == null ) return after;
		if( after == null ) return before;
		return (String column) -> after.apply(before.apply(column));
	}
}
