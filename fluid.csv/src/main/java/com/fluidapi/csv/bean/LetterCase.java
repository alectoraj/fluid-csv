package com.fluidapi.csv.bean;

import java.util.function.UnaryOperator;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LetterCase {
	
	UPPER(String::toUpperCase),
	LOWER(String::toLowerCase);
	// TODO add support for title case
	
	public final @NonNull UnaryOperator<String> transform;
}
