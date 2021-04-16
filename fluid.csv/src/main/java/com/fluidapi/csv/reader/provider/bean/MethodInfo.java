package com.fluidapi.csv.reader.provider.bean;

import java.lang.reflect.Method;

import com.fluidapi.csv.utility.StringPredicates;

import lombok.NonNull;

public class MethodInfo extends ExecutableInfo<Method> {

	public MethodInfo(@NonNull Method it) {
		super(it);
	}
	
	public boolean isGetter() {
		return isNoArgCall()
			// TODO check return type non void
			&& StringPredicates.isGetter.test(it.getName());
	}
	
	public boolean isSetter() {
		return isUnitArgCall()
			// TODO check void return type
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
