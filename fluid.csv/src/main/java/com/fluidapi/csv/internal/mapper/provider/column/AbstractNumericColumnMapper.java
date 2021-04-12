package com.fluidapi.csv.internal.mapper.provider.column;

import static org.apache.commons.lang3.StringUtils.isBlank;

import com.fluidapi.csv.internal.mapper.provider.beans.MemberInfo;

public abstract class AbstractNumericColumnMapper extends AbstractColumnMapper {

	protected AbstractNumericColumnMapper(MemberInfo<?> memberInfo) {
		super(memberInfo);
	}
	
	@Override
	public Object transform(String in) {
		return toNumber(in.strip());
	}
	
	@Override
	protected boolean treatAsEmpty(String input) {
		return isBlank(input);
	}
	
	protected abstract Object toNumber(String in);
	
}
