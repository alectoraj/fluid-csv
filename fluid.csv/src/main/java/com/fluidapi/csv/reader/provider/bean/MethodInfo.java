package com.fluidapi.csv.reader.provider.bean;

import java.lang.reflect.Method;

import com.fluidapi.csv.utility.StringPredicates;

import lombok.NonNull;

public class MethodInfo extends ExecutableInfo<Method> {

	public MethodInfo(@NonNull Method it) {
		super(it);
	}
	
	public Class<?> returnType() {
		return it.getReturnType();
	}
	
	public boolean isGetter() {
		return isNoArgCall()
			&& !returnType().equals(void.class)
			&& StringPredicates.isGetter.test(it.getName());
	}
	
	public boolean isSetter() {
		return isUnitArgCall()
			&& returnType().equals(void.class)
			&& StringPredicates.isSetter.test(it.getName());
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
