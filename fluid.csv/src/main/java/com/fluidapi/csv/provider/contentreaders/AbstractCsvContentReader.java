package com.fluidapi.csv.provider.contentreaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

import com.fluidapi.csv.CsvContentReader;
import com.fluidapi.csv.function.Sequence;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractCsvContentReader implements CsvContentReader {
	
	protected final @NonNull Consumer<BufferedReader> filePreProcessor;

	@Override
	public Sequence<String> lines(Path file) {
		BufferedReader reader = toReader(file);
		filePreProcessor.accept(reader);
		return lines(reader);
	}

	protected BufferedReader toReader(Path file) {
		try {
			return Files.newBufferedReader(file);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
	
	protected abstract Sequence<String> lines(BufferedReader reader);

}
