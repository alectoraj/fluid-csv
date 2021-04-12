package com.fluidapi.csv.internal.mapper.provider.column;

import static com.fluidapi.csv.internal.validation.Requires.requireTrue;
import static java.util.Objects.nonNull;

import java.util.HashMap;
import java.util.Map;

import com.fluidapi.csv.internal.function.ColumnMapper;
import com.fluidapi.csv.internal.function.ColumnMapperInitializer;
import com.fluidapi.csv.internal.mapper.provider.beans.MemberInfo;

public class ColumnMapperIndex {
	
	private static final Map<Class<?>, ColumnMapperInitializer> initializerIndex = apiIndex();

	private static Map<Class<?>, ColumnMapperInitializer> apiIndex() {
		Map<Class<?>, ColumnMapperInitializer> index = new HashMap<>();

		index.put(StringMapper.supports, StringMapper::new);
		index.put(IntegerMapper.supports, IntegerMapper::new);
		index.put(LocalDateMapper.supports, LocalDateMapper::new);
		
		return index;
	}
	
	public static final ColumnMapper mapperOf(MemberInfo<?> memberInfo) {
		ColumnMapperInitializer initializer = initializerIndex.get(memberInfo.targetType());
		requireTrue(nonNull(initializer),
				() -> "the given type %s is not supported, use a custom converter".formatted(memberInfo.targetType()),
				UnsupportedOperationException::new);
		
		return initializer.initialize(memberInfo);
	}
	
}
