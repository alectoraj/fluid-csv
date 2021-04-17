package com.fluidapi.csv.provider.bean;

import java.util.function.Function;

public interface AutoGetter extends Function<Object, String> {
	
	Object get(Object instance);
	String autoGet(Object instance);
	
	@Override
	default String apply(Object t) {
		return autoGet(t);
	}
	
}
