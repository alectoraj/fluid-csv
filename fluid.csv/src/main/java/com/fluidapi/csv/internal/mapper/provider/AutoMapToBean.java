package com.fluidapi.csv.internal.mapper.provider;

import static com.fluidapi.csv.internal.validation.Requires.requireTrue;
import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toMap;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.fluidapi.csv.OrmMapper;
import com.fluidapi.csv.exception.UncheckedException;
import com.fluidapi.csv.internal.function.Setter;
import com.fluidapi.csv.internal.mapper.provider.beans.AnnotatedInfo;
import com.fluidapi.csv.internal.mapper.provider.beans.FieldInfo;
import com.fluidapi.csv.internal.mapper.provider.beans.MemberInfo;
import com.fluidapi.csv.internal.mapper.provider.beans.MethodInfo;

public class AutoMapToBean<T> implements OrmMapper<T> {
	
	private final Constructor<T> constructor;
	private final Map<Integer, Setter> setters;
	
	public AutoMapToBean(Class<T> type) {
		requireNonNull(type, "class type");
		this.constructor = findConstructor(type);
		this.setters = findSetters(type);
	}

	@Override
	public T apply(String[] columns) {
		requireNonNull(columns, "columns");
		requireTrue(columns.length == setters.size(),
				() -> "column size mismatch | expected: %d, actual: %d".formatted(setters.size(), columns.length),
				IllegalArgumentException::new);
		
		try {
			return applyUnchecked(columns);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException("error creating bean reflectively", e);
		}
	}
	
	private T applyUnchecked(String[] columns) throws InstantiationException, IllegalAccessException, InvocationTargetException {
		
		// create instance
		T instance = constructor.newInstance();

		// set values using setters
		IntStream.range(0, columns.length)
		.forEach(index -> {
			setters.get(index)
				.set(instance, columns[index]);
		});
		
		// return populated instance
		return instance;
	}
	
	private static <T> Constructor<T> findConstructor(Class<T> type) {
		try {
			return type.getConstructor();
		} catch (NoSuchMethodException | SecurityException e) {
			throw new UncheckedException("empty constructor not found or not accessible", e);
		}
	}

	private static <T> Map<Integer, Setter> findSetters(Class<T> type) {
		return	Stream
				.concat(
						findFieldSetters(type.getDeclaredFields()),
						findMethodSetters(type.getDeclaredMethods())
				)
				.collect(
						toMap(
							AnnotatedInfo::getCsvColumnIndex,
							MemberInfo::findSetter)
				);
	}

	private static Stream<FieldInfo> findFieldSetters(Field[] fields) {
		return
		stream(fields)
		.map(FieldInfo::new)
		.filter(FieldInfo::isCsvColumn);
	}

	private static Stream<MethodInfo> findMethodSetters(Method[] methods) {
		return
		stream(methods)
		.map(MethodInfo::new)
		.filter(MethodInfo::isCsvColumn)
		.filter(MethodInfo::isFieldSetter);
	}

}
