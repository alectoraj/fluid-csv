package com.fluidapi.csv.internal.mapper.provider;

import static com.fluidapi.csv.internal.validation.Requires.requireTrue;
import static lombok.AccessLevel.PROTECTED;

import com.fluidapi.csv.OrmMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = PROTECTED)
public class PickStringColumn implements OrmMapper<String> {
	
	private final int index;

	@Override
	public String apply(String[] strings) {
		return strings[index];
	}
	
	public static OrmMapper<String> pick(int index) {
		requireTrue(index >= 0, () -> "index is negative " + index, IndexOutOfBoundsException::new);
		return new PickStringColumn(index);
	}

}
