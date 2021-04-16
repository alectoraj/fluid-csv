package com.fluidapi.csv.reader.provider.bean;

import java.util.stream.Stream;

import lombok.NonNull;

public class CsvClassInfo<T> extends ClassInfo<T> {

	public CsvClassInfo(@NonNull Class<T> it) {
		super(it);
	}
	
	public Stream<FieldInfo> csvFields() {
		return fields().filter(FieldInfo::isCsvColumn);
	}

	public Stream<MethodInfo> csvGetters() {
		return methods().filter(MethodInfo::isCsvColumn)
						.filter(MethodInfo::isNoArgCall);
	}
	
	public Stream<MethodInfo> csvSetters() {
		return methods().filter(MethodInfo::isCsvColumn)
						.filter(MethodInfo::isUnitArgCall);
	}

}
