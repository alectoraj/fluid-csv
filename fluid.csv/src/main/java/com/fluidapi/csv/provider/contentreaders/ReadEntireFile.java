package com.fluidapi.csv.provider.contentreaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.function.Consumer;

import com.fluidapi.csv.CsvContentReader;
import com.fluidapi.csv.function.Sequence;
import com.fluidapi.csv.function.provider.CloseOnceSequence;

public class ReadEntireFile extends AbstractCsvContentReader implements CsvContentReader {
	
	public ReadEntireFile(Consumer<BufferedReader> filePreProcessor) {
		super(filePreProcessor);
	}
	
	@Override
	public Sequence<String> lines(BufferedReader reader) {
		return new LinesIterator(reader);
	}
	
	protected static class LinesIterator extends CloseOnceSequence<String> implements Sequence<String> {
		
		protected BufferedReader reader;
		protected String line;
		protected boolean hasRead;
		
		public LinesIterator(BufferedReader reader) {
			this.reader = reader;
		}
		
		@Override
		protected boolean hasMoreElements() {
			if( !hasRead ) {
				line = readLine();
				hasRead = true;
			}
			
			return line != null;
		}

		@Override
		public String nextElement() {
			hasNext();
			
			String it = line;
			line = null;
			hasRead = false;
			
			return it;
		}

		@Override
		public void closeResources() {
			try {
				reader.close();
				reader = null;
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			}
		}

		protected String readLine() {
			try {
				return reader.readLine();
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			}
		}
	}
}
