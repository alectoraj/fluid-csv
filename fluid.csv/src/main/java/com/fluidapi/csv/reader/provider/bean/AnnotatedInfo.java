package com.fluidapi.csv.reader.provider.bean;

import static com.fluidapi.csv.validaton.FailCheck.failIf;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

import com.fluidapi.csv.annotations.CsvColumn;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AnnotatedInfo<T extends AnnotatedElement> {
	
	@NonNull
	public final T it;
	
	public boolean hasAnnotation(Class<? extends Annotation> typeOfAnnotation) {
		return it.isAnnotationPresent(typeOfAnnotation);
	}
	
	public <A extends Annotation> A findAnnotation(Class<A> typeOfAnnotation) {
		return it.getAnnotation(typeOfAnnotation);
	}

	public boolean isCsvColumn() {
		return hasAnnotation(CsvColumn.class);
	}
	
	public int getCsvColumnIndex() {
		
		// get and also validate it
		int index = findAnnotation(CsvColumn.class).value();
		failIf(index < 0, () -> "negative index with + " + it);
		
		// return validated index
		return index;
	}
	
}
