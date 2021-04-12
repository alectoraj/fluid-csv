package com.fluidapi.csv.internal.mapper.provider.beans;

import static lombok.AccessLevel.PROTECTED;

import java.lang.reflect.AnnotatedElement;

import com.fluidapi.csv.config.CsvColumn;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = PROTECTED)
public abstract class AnnotatedInfo<T extends AnnotatedElement> {
	
	@NonNull
	protected final T member;

	public boolean isCsvColumn() {
		return member.isAnnotationPresent(CsvColumn.class);
	}
	public CsvColumn getCsvColumnInfo() {
		return member.getAnnotation(CsvColumn.class);
	}
	public int getCsvColumnIndex() {
		return getCsvColumnInfo().value();
	}
	
}
