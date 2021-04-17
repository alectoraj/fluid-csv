package com.fluidapi.csv.reader.provider.deserializer;

import com.fluidapi.csv.writer.CsvColumnJoiner;

public class PickString extends PickMapped<String> implements CsvColumnJoiner {

	public PickString(int index) {
		super(index, t -> t);
	}

	@Override
	public String join(String[] columns) {
		return convert(columns);
	}

	@Override
	public String apply(String[] t) {
		return super.apply(t);
	}

}
