package com.fluidapi.csv.internal.function;

public interface ColumnMapper extends Transformation {
	
	Object transform(String in);
	
	@Override
	default Object transform(Object in) {
		return transform((String) in);
	}
}
