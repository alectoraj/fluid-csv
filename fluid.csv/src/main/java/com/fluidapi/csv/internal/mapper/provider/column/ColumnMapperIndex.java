package com.fluidapi.csv.internal.mapper.provider.column;

import static com.fluidapi.csv.internal.validation.Requires.requireTrue;
import static java.util.Arrays.stream;
import static java.util.Objects.nonNull;

import java.util.HashMap;
import java.util.Map;

import com.fluidapi.csv.internal.function.ColumnMapper;
import com.fluidapi.csv.internal.function.ColumnMapperInitializer;
import com.fluidapi.csv.internal.mapper.provider.beans.MemberInfo;

public class ColumnMapperIndex {
	
	private static final Map<Class<?>, ColumnMapperInitializer> initializerIndex = new HashMap<>();

	static {
		putKeys(StringMapper.supports, StringMapper::new);
		putKeys(IntegerMapper.supports, IntegerMapper::new);
		putKeys(LocalDateMapper.supports, LocalDateMapper::new);
	}
	
	private static void putKeys(Class<?>[] keys, ColumnMapperInitializer initializer) {
		stream(keys).forEach(key -> initializerIndex.put(key, initializer));
	}
	
	public static final ColumnMapper mapperOf(MemberInfo<?> memberInfo) {
		ColumnMapperInitializer initializer = initializerIndex.get(memberInfo.targetType());
		requireTrue(nonNull(initializer),
				() -> "the given type %s is not supported, use a custom converter".formatted(memberInfo.targetType()),
				UnsupportedOperationException::new);
		
		return initializer.initialize(memberInfo);
	}
	
}
