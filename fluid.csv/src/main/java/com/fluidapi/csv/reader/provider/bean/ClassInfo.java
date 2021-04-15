package com.fluidapi.csv.reader.provider.bean;

import static java.util.Arrays.stream;

import java.util.Comparator;
import java.util.stream.Stream;

import lombok.NonNull;

public class ClassInfo<T> extends AnnotatedInfo<Class<T>> implements TypeInfo<Class<T>> {

	public ClassInfo(@NonNull Class<T> it) {
		super(it);
	}

	@Override
	public Class<?> getType() {
		return it;
	}
	
	/**
	 * sorts by least number of parameters first
	 */
	public Stream<ConstructorInfo> constructors() {
		return stream(it.getConstructors())
				.map(ConstructorInfo::new)
				.sorted(Comparator.comparingInt(ConstructorInfo::getParameterCount));
	}
	
	public Stream<FieldInfo> fields() {
		return stream(it.getDeclaredFields())
				.map(FieldInfo::new);
	}

	public Stream<MethodInfo> methods() {
		return stream(it.getDeclaredMethods())
				.map(MethodInfo::new);
	}
	public Stream<MethodInfo> getters() {
		return methods().filter(MethodInfo::isGetter);
	}
	public Stream<MethodInfo> setters() {
		return methods().filter(MethodInfo::isSetter);
	}

}
