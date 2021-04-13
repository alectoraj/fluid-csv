package com.fluidapi.csv.internal.mapper.provider.beans;

import static lombok.AccessLevel.PROTECTED;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

import com.fluidapi.csv.annotations.CsvColumn;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = PROTECTED)
public abstract class AnnotatedInfo<T extends AnnotatedElement> {
	
	@NonNull
	protected final T member;

	public boolean hasAnnotation(Class<? extends Annotation> annotationClass) {
		return member.isAnnotationPresent(annotationClass);
	}
	public <A extends Annotation> A findAnnotation(Class<A> annotationClass) {
		return member.getAnnotation(annotationClass);
	}

	public boolean isCsvColumn() {
		return hasAnnotation(CsvColumn.class);
	}
	public CsvColumn getCsvColumnInfo() {
		return findAnnotation(CsvColumn.class);
	}
	public int getCsvColumnIndex() {
		return getCsvColumnInfo().value();
	}
	
}
