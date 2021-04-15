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
	
	@Override
	public boolean equals(Object object) {
		if( object instanceof MethodInfo other ) {
			return it.equals(other.it);
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return it.hashCode();
	}

}
