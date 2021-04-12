package com.fluidapi.csv.internal.mapper.provider;

import static com.fluidapi.csv.internal.validation.Requires.requireTrue;

import com.fluidapi.csv.OrmMapper;

public class PickStringColumn implements OrmMapper<String> {
	
	private final int index;
	
	public PickStringColumn(int index) {
		requireTrue(index >= 0, () -> "index is negative " + index, IndexOutOfBoundsException::new);
		this.index = index;
	}

	@Override
	public String apply(String[] strings) {
		return strings[index];
	}

}
