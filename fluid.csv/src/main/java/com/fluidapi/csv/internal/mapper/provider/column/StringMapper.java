package com.fluidapi.csv.internal.mapper.provider.column;

import com.fluidapi.csv.internal.mapper.provider.beans.MemberInfo;

public class StringMapper extends AbstractColumnMapper {
	
	public static final Class<String> supports = String.class;

	public StringMapper(MemberInfo<?> memberInfo) {
		super(memberInfo);
	}
	
	@Override
	public Object transform(Object in) {
		return in;
	}
	
	@Override
	public Object transform(String in) {
		return in;
	}
	
}
