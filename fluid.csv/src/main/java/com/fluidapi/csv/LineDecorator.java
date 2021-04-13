package com.fluidapi.csv;

import java.util.function.UnaryOperator;

@FunctionalInterface
public interface LineDecorator extends UnaryOperator<String[]> {
	
	String[] decorate(String[] columns);
	
	@Override
	default String[] apply(String[] t) {
		return decorate(t);
	}
	
}
