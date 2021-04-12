package com.fluidapi.csv.internal.mapper.provider.column;

import java.util.function.UnaryOperator;

import com.fluidapi.csv.config.StripString;
import com.fluidapi.csv.internal.mapper.provider.beans.MemberInfo;

public class StringMapper extends AbstractColumnMapper {

	public static final Class<String>[] supports = support(String.class);

	private UnaryOperator<String> mapAsConfigured;
	
	public StringMapper(MemberInfo<?> memberInfo) {
		super(memberInfo);
		configureMapping();
	}

	@Override
	public Object transform(String in) {
		return mapAsConfigured.apply(in);
	}
	
	private void configureMapping() {
		if( memberInfo.hasAnnotation(StripString.class)
			&& memberInfo.findAnnotation(StripString.class).value() ) {
			
			mapAsConfigured = String::strip;
		} else {
			
			mapAsConfigured = t -> t;
		}
	}
	
}
