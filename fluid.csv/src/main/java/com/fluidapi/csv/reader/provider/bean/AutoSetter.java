package com.fluidapi.csv.reader.provider.bean;

import java.util.function.BiConsumer;

public interface AutoSetter extends BiConsumer<Object, String[]> {
	
	void set(Object instance, Object value);
	void autoSet(Object instance, String[] columns);
	
	@Override
	default void accept(Object t, String[] u) {
		autoSet(t, u);
	}
	
}
