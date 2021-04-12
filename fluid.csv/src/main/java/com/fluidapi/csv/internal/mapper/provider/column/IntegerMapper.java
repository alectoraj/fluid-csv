package com.fluidapi.csv.internal.mapper.provider.column;

import com.fluidapi.csv.internal.mapper.provider.beans.MemberInfo;

public class IntegerMapper extends AbstractNumericColumnMapper {
	
	public static final Class<Integer>[] supports = support(Integer.class, int.class);

	public IntegerMapper(MemberInfo<?> memberInfo) {
		super(memberInfo);
	}
	
	@Override
	protected Object toNumber(String in) {
		return Integer.valueOf(in);
	}
		
}
