package com.fluidapi.csv.provider.bean;

import java.lang.reflect.Parameter;

import lombok.NonNull;

public class ParameterInfo extends AnnotatedInfo<Parameter> implements TypeInfo<Parameter> {

	public ParameterInfo(@NonNull Parameter it) {
		super(it);
	}
	
	@Override
	public Class<?> getType() {
		return it.getType();
	}

}
