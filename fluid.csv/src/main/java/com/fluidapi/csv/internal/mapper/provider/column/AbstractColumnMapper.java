package com.fluidapi.csv.internal.mapper.provider.column;

import static lombok.AccessLevel.PROTECTED;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import com.fluidapi.csv.internal.function.ColumnMapper;
import com.fluidapi.csv.internal.mapper.provider.beans.MemberInfo;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = PROTECTED)
public abstract class AbstractColumnMapper implements ColumnMapper {
	
	@SafeVarargs
	protected static <T> Class<T>[] support(Class<T>...classes) {
		return classes;
	}
	
	@NonNull
	protected final MemberInfo<?> memberInfo;

	@Override
	public Object transform(Object in) {
		if( in == null ) return null;
		
		String input = (String) in;
		if( treatAsEmpty(input) ) return input;
		
		return transform(input);
	}

	protected boolean treatAsEmpty(String input) {
		return isEmpty(input);
	}
	
}
