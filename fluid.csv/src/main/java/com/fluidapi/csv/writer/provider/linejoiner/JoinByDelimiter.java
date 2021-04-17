package com.fluidapi.csv.writer.provider.linejoiner;

import com.fluidapi.csv.writer.CsvColumnJoiner;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JoinByDelimiter implements CsvColumnJoiner {
	
	final @NonNull String delimiter;

	@Override
	public String join(String[] columns) {
		return String.join(delimiter, columns);
	}

}
