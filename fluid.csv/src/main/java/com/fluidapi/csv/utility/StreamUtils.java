package com.fluidapi.csv.utility;

import static java.util.Arrays.stream;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Utility methods revolving around {@link Stream}, {@link IntStream},
 * {@link LongStream}, {@link DoubleStream}
 * 
 * @author Arindam Biswas
 * @since 1.0
 */
public interface StreamUtils {
	
	int THRESHOLD = 2 << 5;

	/**
	 * creates parallel stream if length greater than {@value #THRESHOLD}
	 * 
	 * @param stream          the stream to possibly parallelize
	 * @param estimatedLength since measuring length in stream would end it, asking
	 *                        the estimated or real length from you
	 * @return a parallel stream if large enough, or as is if small enough.
	 */
	static IntStream fastStream(IntStream stream, int estimatedLength) {
		return estimatedLength > THRESHOLD ? stream.parallel() : stream;
	}

	/**
	 * creates parallel stream if length greater than 64, else a regular stream
	 * 
	 * @param ints {@code int} array of elements
	 * @return a parallel stream if large enough, or as is if small enough.
	 */
	static IntStream fastStream(int... ints) {
		return fastStream(stream(ints), ints.length);
	}

	/**
	 * creates parallel stream if length greater than {@value #THRESHOLD}
	 * 
	 * @param stream          the stream to possibly parallelize
	 * @param estimatedLength since measuring length in stream would end it, asking
	 *                        the estimated or real length from you
	 * @return a parallel stream if large enough, or as is if small enough.
	 */
	static <T> Stream<T> fastStream(Stream<T> stream, int estimatedLength) {
		return estimatedLength > THRESHOLD ? stream.parallel() : stream;
	}

	/**
	 * creates parallel stream if length greater than 64, else a regular stream
	 * 
	 * @param ints {@code int} array of elements
	 * @return a parallel stream if large enough, or as is if small enough.
	 */
	@SafeVarargs
	static <T> Stream<T> fastStream(T...ts) {
		return fastStream(stream(ts), ts.length);
	}
}
