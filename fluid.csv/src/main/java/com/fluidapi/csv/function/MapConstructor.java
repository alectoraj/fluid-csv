package com.fluidapi.csv.function;

import java.util.function.BiFunction;

import com.fluidapi.csv.provider.bean.AnnotatedInfo;
import com.fluidapi.csv.provider.bean.TypeInfo;

@FunctionalInterface
public interface MapConstructor<T> extends BiFunction<TypeInfo<?>, AnnotatedInfo<?>, T> {
	
	T construct(TypeInfo<?> typeInfo, AnnotatedInfo<?> origin);
	
	@Override
	default T apply(TypeInfo<?> t, AnnotatedInfo<?> u) {
		return construct(t, u);
	}
	
}
