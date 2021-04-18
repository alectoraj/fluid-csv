package com.fluidapi.csv.provider.bean;

import static java.util.Arrays.stream;

import java.lang.reflect.Constructor;
import java.util.Comparator;
import java.util.Optional;
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
	
	public Optional<ConstructorInfo<T>> defaultConstructor() {
		return constructors()
				.filter(ConstructorInfo::isDefaultConstructor)
				.findAny();
	}
	
	/**
	 * sorts by least number of parameters first
	 */
	@SuppressWarnings("unchecked")
	public Stream<ConstructorInfo<T>> constructors() {
		return stream(it.getConstructors())
				.map(constructor -> (Constructor<T>) constructor)
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
