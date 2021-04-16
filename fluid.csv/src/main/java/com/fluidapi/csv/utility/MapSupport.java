package com.fluidapi.csv.utility;

import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fluidapi.csv.function.MapConstructor;
import com.fluidapi.csv.reader.provider.bean.AnnotatedInfo;
import com.fluidapi.csv.reader.provider.bean.TypeInfo;

public class MapSupport<T> {
	
	protected final Map<Class<?>, MapConstructor<T>> supportMap;
	
	public MapSupport() {
		supportMap = new ConcurrentHashMap<>();
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

	public void register(Class<?> type, MapConstructor<T> constructor) {
		supportMap.put(type, constructor);
	}
	public void register(MapConstructor<T> constructor, Class<?>...types) {
		requireNonNull(types, "types");
		stream(types).forEach(type -> register(type, constructor));
	}
	
	@Override
	public String toString() {
		return supportMap.toString();
	}
}
