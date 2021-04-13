package com.fluidapi.csv.internal.mapper.provider.column;

import static org.apache.commons.lang3.StringUtils.isBlank;

import com.fluidapi.csv.internal.mapper.provider.beans.MemberInfo;

public abstract class AbstractParseableColumnMapper extends AbstractColumnMapper {

	protected AbstractParseableColumnMapper(MemberInfo<?> memberInfo) {
		super(memberInfo);
	}
	
	@Override
	public final Object transform(String in) {
		return parse(in.strip());
	}
	
	@Override
	protected boolean treatAsEmpty(String input) {
		return isBlank(input);
	}
	
	protected abstract Object parse(String in);
	
}
