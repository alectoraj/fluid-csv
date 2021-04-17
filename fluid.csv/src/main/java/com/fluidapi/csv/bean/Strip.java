package com.fluidapi.csv.bean;

import java.util.function.UnaryOperator;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Strip {

	LEFT(String::stripLeading),
	RIGHT(String::stripTrailing),
	AROUND(String::strip);

	public final @NonNull UnaryOperator<String> strip;
}
