package com.fluidapi.csv.internal.mapper.provider.column;

import com.fluidapi.csv.internal.mapper.provider.beans.MemberInfo;

public class IntegerMapper extends AbstractColumnMapper {
	
	public static final Class<Integer> supports = Integer.class;

	public IntegerMapper(MemberInfo<?> memberInfo) {
		super(memberInfo);
	}
	
	@Override
	public Object transform(String in) {
		return Integer.valueOf(in);
	}
	
}
