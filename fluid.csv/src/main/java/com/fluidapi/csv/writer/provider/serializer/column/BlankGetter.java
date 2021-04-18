package com.fluidapi.csv.writer.provider.serializer.column;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import com.fluidapi.csv.provider.bean.AutoGetter;

public class BlankGetter implements AutoGetter {

	@Override
	public Object get(Object instance) {
		return EMPTY;
	}

	@Override
	public String autoGet(Object instance) {
		return EMPTY;
	}

}
