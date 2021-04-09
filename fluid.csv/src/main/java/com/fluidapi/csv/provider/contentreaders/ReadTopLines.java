package com.fluidapi.csv.provider.contentreaders;

import java.io.BufferedReader;
import java.util.function.Consumer;
import java.util.function.Function;

import com.fluidapi.csv.CsvContentReader;
import com.fluidapi.csv.function.Queue;
import com.fluidapi.csv.function.Sequence;
import com.fluidapi.csv.function.provider.CloseOnceSequence;
import com.fluidapi.csv.function.provider.FixedLengthQueue;
import com.fluidapi.csv.provider.contentreaders.ReadEntireFile.LinesIterator;

/**
 * Current implementation for negative n retains n number of lines in memory at
 * any given time, and it naturally ends up reading the whole file.
 * 
 * <p>
 * Hence, it is recommended to avoid big scalar value of n for negative, or at
 * least be aware of memory requirement and arrange accordingly.
 * </p>
 * 
 * @author Arindam Biswas
 */
public class ReadTopLines extends AbstractCsvContentReader implements CsvContentReader {
	
	private final Function<BufferedReader, Sequence<String>> lineIterator;
	
	public ReadTopLines(int lineCount, Consumer<BufferedReader> filePreProcessor) {
		super(filePreProcessor);
		lineIterator =	OnlyTopNLinesIterator.isSuitable(lineCount) ?
							reader -> new OnlyTopNLinesIterator(reader, lineCount) :
							
						TopLinesExceptLastNIterator.isSuitable(lineCount) ?
							reader -> new TopLinesExceptLastNIterator(reader, -lineCount) :
							
						reader -> new ReadNoLine();
	}

	@Override
	public Sequence<String> lines(BufferedReader reader) {
		return lineIterator.apply(reader);
	}
	
	protected static class ReadNoLine implements Sequence<String> {

		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public String next() {
			return null;
		}
		
	}
	
	protected static class OnlyTopNLinesIterator extends ReadEntireFile.LinesIterator implements Sequence<String> {
		
		protected int lineLimit;
		protected int readLines;
		
		public OnlyTopNLinesIterator(BufferedReader reader, int lineCount) {
			super(reader);
			this.lineLimit = lineCount;
			this.readLines = 0;
		}
		
		@Override
		protected boolean hasMoreElements() {
			boolean hasRead = super.hasRead;
			boolean hasMore = super.hasMoreElements();
			
			if( !hasRead ) {
				hasMore = hasMore && (++readLines <= lineLimit);
			}
			
			return hasMore;
		}
		
		protected static boolean isSuitable(int toReadLines) {
			return toReadLines > 0;
		}
		
	}
	
	protected static class TopLinesExceptLastNIterator extends CloseOnceSequence<String> implements Sequence<String> {
		
		protected LinesIterator fileReader;
		protected Queue<String> linesQueue;
		
		public TopLinesExceptLastNIterator(BufferedReader reader, int lineCount) {
			fileReader = new LinesIterator(reader);
			
			linesQueue = new FixedLengthQueue<>(lineCount);
			fillQueue(lineCount);
		}

		@Override
		protected String nextElement() {
			if( !hasNext() ) {
				return null;
			}
			
			String next = linesQueue.dequeue();
			linesQueue.enqueue(fileReader.next());
			
			return next;
		}

		@Override
		protected boolean hasMoreElements() {
			return fileReader.hasNext();
		}

		@Override
		protected void closeResources() {
			fileReader.close();
			linesQueue = null;
		}

		protected void fillQueue(int lineLimit) {
			for (int readLines = 0; fileReader.hasNext() && readLines < lineLimit; readLines++) {
				linesQueue.enqueue(fileReader.next());
			}
		}
		
		protected static boolean isSuitable(int toReadLines) {
			return toReadLines < 0;
		}
		
	}
}
