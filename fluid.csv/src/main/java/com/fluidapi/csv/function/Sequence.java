package com.fluidapi.csv.function;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;
import static java.util.Spliterator.NONNULL;
import static java.util.Spliterator.ORDERED;
import static java.util.Spliterators.spliteratorUnknownSize;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.fluidapi.csv.function.provider.SequenceWithConversion;

public interface Sequence<T> extends Iterator<T>, AutoCloseable {
	
	default Optional<T> tryNext() {
		return hasNext()
			 ? ofNullable(next())
			 : Optional.empty();
	}
	
	default Stream<T> stream() {

        return StreamSupport.stream(spliteratorUnknownSize(this, ORDERED | NONNULL), false);
	}
	
	default <U> Sequence<U> then(Function<T, U> conversion) {
		requireNonNull(conversion, "conversion");
		return new SequenceWithConversion<>(this, conversion);
	}
	
	@Override
	default void close() {
	}
	
}
