package com.fluidapi.csv.reader.provider.bean;

import java.util.stream.Stream;

import lombok.NonNull;

public class CsvClassInfo<T> extends ClassInfo<T> {

	public CsvClassInfo(@NonNull Class<T> it) {
		super(it);
	}

	/**
	 * sorts by least number of parameters first
	 */
	public Stream<ConstructorInfo<T>> csvConstructors() {
		return constructors().filter(ConstructorInfo::isCsvConstructor);
	}
	
	public Stream<FieldInfo> csvFields() {
		return fields().filter(FieldInfo::isCsvColumn);
	}

	public Stream<MethodInfo> csvGetters() {
		return getters().filter(MethodInfo::isCsvColumn);
	}
	
	public Stream<MethodInfo> csvSetters() {
		return setters().filter(MethodInfo::isCsvColumn);
	}

}
