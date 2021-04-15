package com.fluidapi.csv.reader.provider.bean;

import java.lang.reflect.Method;

import lombok.NonNull;

public class MethodInfo extends ExecutableInfo<Method> {

	public MethodInfo(@NonNull Method it) {
		super(it);
	}
	
	public boolean isGetter() {
		return isNoArgCall()
			&& it.getName().matches("^(get|is)[A-Z]");
	}
	
	public boolean isSetter() {
		return isUnitArgCall()
			&& it.getName().matches("^(set)[A-Z]");
	}

}
