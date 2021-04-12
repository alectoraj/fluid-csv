package com.fluidapi.csv.internal.mapper.provider.column;

import static org.apache.commons.lang3.StringUtils.isBlank;

import com.fluidapi.csv.internal.mapper.provider.beans.MemberInfo;

public abstract class AbstractNumericColumnMapper extends AbstractColumnMapper {

	protected AbstractNumericColumnMapper(MemberInfo<?> memberInfo) {
		super(memberInfo);
	}
	
	@Override
	protected boolean treatAsEmpty(String input) {
		return isBlank(input);
	}
	
}
