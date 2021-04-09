package com.fluidapi.csv.provider.contentreaders;

import static com.fluidapi.csv.Csv.skip;

import java.io.BufferedReader;
import java.util.function.Consumer;

import com.fluidapi.csv.Csv;
import com.fluidapi.csv.CsvContentReader;
import com.fluidapi.csv.function.LimitedQueue;
import com.fluidapi.csv.function.Sequence;
import com.fluidapi.csv.function.provider.CloseOnceSequence;
import com.fluidapi.csv.function.provider.FixedLengthQueue;

/**
 * Current implementation for positive n retains up to n number of lines in
 * memory at any given time.
 * <p>
 * Hence, it is advised to avoid using big number with {@code tail(positive)} or
 * at least be aware of the respective memory requirement.
 * </p>
 * 
 * @author Arindam Biswas
 */
public class ReadLastLines extends AbstractCsvContentReader implements CsvContentReader {
	
	protected int lineLimit;
	
	protected ReadLastLines(int lineCount, Consumer<BufferedReader> filePreProcessor) {
		super(filePreProcessor);
		lineLimit = lineCount;
	}

	@Override
	public Sequence<String> lines(BufferedReader reader) {
		return new OnlyLastNLines(reader, lineLimit);
	}
	
	public static CsvContentReader of(int lineCount, Consumer<BufferedReader> filePreProcessor) {
		return OnlyLastNLines.isSuitable(lineCount)
			 ? new ReadLastLines(lineCount, filePreProcessor)
			 : Csv.lines(skip(-lineCount));
	}
	
	protected static class OnlyLastNLines extends CloseOnceSequence<String> implements Sequence<String> {
		
		protected LimitedQueue<String> lineQueue;
		
		public OnlyLastNLines(BufferedReader reader, int lineCount) {
			this.lineQueue = new FixedLengthQueue<>(lineCount);
			this.fillQueue(reader);
		}
		
		@Override
		protected boolean hasMoreElements() {
			return !lineQueue.isEmpty();
		}
		
		@Override
		protected String nextElement() {
			return lineQueue.dequeue();
		}
		
		@Override
		protected void closeResources() {
			lineQueue = null;
		}

		protected void fillQueue(BufferedReader reader) {
			reader.lines().forEach(line -> {
				
				// discard top items from queue
				if( lineQueue.isFull() ) {
					lineQueue.dequeue();
				}
				
				lineQueue.enqueue(line);
			});
		}
		
		protected static boolean isSuitable(int toReadLines) {
			return toReadLines > 0;
		}
		
	}
}
