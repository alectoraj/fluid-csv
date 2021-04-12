package com.fluidapi.csv.internal.function;

import java.util.function.Function;

import com.fluidapi.csv.internal.mapper.provider.beans.MemberInfo;

public interface ColumnMapperInitializer extends Function<MemberInfo<?>, ColumnMapper> {
	
	ColumnMapper initialize(MemberInfo<?> info);
	
	@Override
	default ColumnMapper apply(MemberInfo<?> t) {
		return initialize(t);
	}
	
}
