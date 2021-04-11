package com.fluidapi.csv.internal.function.provider;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class TestStringExtractor {

	@Test
	public void testNull() {
		assertThrows(NullPointerException.class, () -> StringExtractor.of(null));
	}
	
	@Test
	public void testEmpty() {
		StringExtractor extractor = StringExtractor.of(EMPTY);
		assertEquals("", extractor.apply(-1));
		assertEquals("", extractor.apply(0));
		assertEquals("", extractor.apply(1));
	}
	
	@Test
	public void testNonEmpty() {
		StringExtractor extractor = StringExtractor.of("abcd");
		assertEquals("", extractor.apply(-1));
		assertEquals("", extractor.apply(0));

		assertEquals("a", extractor.apply(1));
		assertEquals("bc", extractor.apply(2));
		assertEquals("d", extractor.apply(2));
		assertEquals("", extractor.apply(2));
	}
	
}
