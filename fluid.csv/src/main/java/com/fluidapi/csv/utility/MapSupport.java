package com.fluidapi.csv.utility;

import java.util.HashMap;
import java.util.Map;

import com.fluidapi.csv.function.MapConstructor;
import com.fluidapi.csv.reader.provider.bean.AnnotatedInfo;
import com.fluidapi.csv.reader.provider.bean.TypeInfo;

public class MapSupport<T> {
	
	private final Map<Class<?>, MapConstructor<T>> supportMap;
	
	public MapSupport() {
		supportMap = new HashMap<>();
	}

	public boolean supports(TypeInfo<?> typeInfo) {
		return supports(typeInfo.getType());
	}
	public boolean supports(Class<?> type) {
		return supportMap.containsKey(type);
	}

	public T of(TypeInfo<?> typeInfo, AnnotatedInfo<?> origin) {
		return supportMap.get(typeInfo.getType()).apply(typeInfo, origin);
	}
	
	public void register(Class<?> primitiveType, MapConstructor<T> constructor) {
		supportMap.put(primitiveType, constructor);
	}
	
	@Override
	public String toString() {
		return supportMap.toString();
	}
}
