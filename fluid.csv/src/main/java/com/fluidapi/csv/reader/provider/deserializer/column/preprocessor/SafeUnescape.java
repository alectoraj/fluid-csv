package com.fluidapi.csv.reader.provider.deserializer.column.preprocessor;

import com.fluidapi.csv.annotations.CsvUnescape;
import com.fluidapi.csv.reader.provider.bean.AnnotatedInfo;
import com.fluidapi.csv.reader.provider.deserializer.column.MapSafe;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SafeUnescape extends MapSafe<String> {
	
	private final char escape;

	public SafeUnescape(AnnotatedInfo<?> origin) {
		this(origin.findAnnotation(CsvUnescape.class).value());
	}
	
	// pattern is buggy with \ character
	// hence using plain char array operation

	@Override
	protected String mapSafe(String column) {
		char[] in = column.toCharArray();
		StringBuilder out = new StringBuilder(in.length);
		
		boolean skipped = false;
		for (char it : in) {
			if( skipped ) {
				// if skipped, don't skip,
				// and reset flag
				skipped = false;
				
			} else if( it == escape ) {
				// if last one wasn't escape
				// and this one is,
				// then mark skipped and skip for real
				skipped = true;
				continue;
			}

			// if not skipped, append it
			out.append(it);
		}
		
		return out.toString();
	}
	
	public static boolean supports(AnnotatedInfo<?> origin) {
		return origin.hasAnnotation(CsvUnescape.class);
	}
	
}
