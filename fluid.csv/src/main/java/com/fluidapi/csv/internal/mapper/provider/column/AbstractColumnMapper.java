package com.fluidapi.csv.internal.mapper.provider.column;

import static lombok.AccessLevel.PROTECTED;

import com.fluidapi.csv.internal.function.ColumnMapper;
import com.fluidapi.csv.internal.mapper.provider.beans.MemberInfo;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = PROTECTED)
public abstract class AbstractColumnMapper implements ColumnMapper {
	
	@NonNull
	protected final MemberInfo<?> memberInfo;

}
