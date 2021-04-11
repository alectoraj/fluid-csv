package com.fluidapi.csv.internal.mapper.provider;

import static com.fluidapi.csv.internal.validation.Requires.requireTrue;
import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;
import static lombok.AccessLevel.PROTECTED;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.fluidapi.csv.OrmMapper;
import com.fluidapi.csv.config.CsvColumn;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = PROTECTED)
public class AutoMapToBean<T> implements OrmMapper<T> {
	
	private final Constructor<T> constructor;
	private final Map<Integer, Setter> setters;

	@Override
	public T apply(String[] columns) {
		requireNonNull(columns, "columns");
		requireTrue(columns.length == setters.size(),
				() -> format("column size mismatch | expected: %d, actual: %d", setters.size(), columns.length),
				IllegalArgumentException::new);
		
		try {
			return applyUnchecked(columns);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException("error creating bean reflectively", e);
		}
	}
	
	private T applyUnchecked(String[] columns) throws InstantiationException, IllegalAccessException, InvocationTargetException {
		T t = constructor.newInstance();
		
		for (int index = 0, length = setters.size(); index < length; index++) {
			Setter setter = setters.get(index);
			Object value = setter.transform.apply(columns[index]);
			setter.method.invoke(t, value);
		}
		
		return t;
	}

	static record Setter(Method method, Function<String, Object> transform) {
		Setter {
			requireNonNull(method, "method");
			requireNonNull(transform, "transform");
		}
	}
	
	static record FieldInfo(Field field) {

		boolean isCsvColumn() {
			return field.getAnnotation(CsvColumn.class) != null;
		}
		int csvIndex() {
			return field.getAnnotation(CsvColumn.class).value();
		}
		Setter toSetter() {
			return new Setter(findSetter(), t -> t);
		}
		Method findSetter() {
			try {
				return field.getDeclaringClass()
						.getMethod(toSetterName(), field.getType());
			} catch (NoSuchMethodException | SecurityException e) {
				throw new RuntimeException("setter not found", e);
			}
		}
		String toSetterName() {
			StringBuilder name = new StringBuilder("set").append(field.getName());
			name.setCharAt(3, Character.toUpperCase(name.charAt(3)));
			return name.toString();
		}
		
	}
	
	static record MethodInfo(Method method) {

		boolean isCsvColumn() {
			return method.getAnnotation(CsvColumn.class) != null;
		}
		int csvIndex() {
			return method.getAnnotation(CsvColumn.class).value();
		}
		boolean isSingleParameter() {
			return method.getParameterCount() == 1;
		}
		Setter toSetter() {
			return new Setter(method, t -> t);
		}
		
	}
	
	public static <T> OrmMapper<T> of(Class<T> type) {
		requireNonNull(type, "class type");
		
		Map<Integer, Setter> setters = new HashMap<>();
		putSettersOfFields(setters, type.getDeclaredFields());
		putSettersOfMethods(setters, type.getDeclaredMethods());
		
		try {
			return new AutoMapToBean<>(type.getConstructor(), setters);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new RuntimeException("empty constructor not found", e);
		}
	}

	private static void putSettersOfFields(Map<Integer, Setter> setters, Field[] fields) {
		stream(fields)
		.map(FieldInfo::new)
		.filter(FieldInfo::isCsvColumn)
		.forEach(field -> setters.put(field.csvIndex(), field.toSetter()));
	}

	private static void putSettersOfMethods(Map<Integer, Setter> setters, Method[] methods) {
		stream(methods)
		.map(MethodInfo::new)
		.filter(MethodInfo::isCsvColumn)
		.filter(MethodInfo::isSingleParameter)
		.forEach(method -> setters.put(method.csvIndex(), method.toSetter()));
	}

}
