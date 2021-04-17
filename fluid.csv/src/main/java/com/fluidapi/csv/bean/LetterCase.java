package com.fluidapi.csv.bean;

import java.util.function.UnaryOperator;

import com.fluidapi.csv.utility.StringUtils;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LetterCase {
	
	UPPER(String::toUpperCase),
	LOWER(String::toLowerCase),
	TITLE(StringUtils::toTitleCase);
	
	public final @NonNull UnaryOperator<String> transform;
}
