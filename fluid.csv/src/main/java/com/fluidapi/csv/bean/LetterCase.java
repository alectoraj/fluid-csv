package com.fluidapi.csv.bean;

import java.util.function.UnaryOperator;

import com.fluidapi.csv.utility.StringUtils;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LetterCase {
	
	/**
	 * shifts every character to upper case letter
	 */
	UPPER(String::toUpperCase),
	
	/**
	 * shifts every character to lower case letter
	 */
	LOWER(String::toLowerCase),
	
	/**
	 * every word individually will be title cased.
	 * <p>
	 * every consecutive set of upper or lower case letters, numbers and underscore
	 * are treated as a single word. any other character, like space, line break,
	 * single or double quote, any symbol are treated as a word break
	 * </p>
	 */
	TITLE(StringUtils::toTitleCase);
	
	public final @NonNull UnaryOperator<String> transform;
}
