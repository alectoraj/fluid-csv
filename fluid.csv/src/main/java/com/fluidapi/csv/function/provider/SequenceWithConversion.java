package com.fluidapi.csv.function.provider;

import static java.util.Objects.requireNonNull;

import java.util.function.Function;

import com.fluidapi.csv.function.Sequence;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SequenceWithConversion<T, R> implements Sequence<R> {
	
	private @NonNull Sequence<T> original;
	private @NonNull Function<T, R> conversion;

	@Override
	public boolean hasNext() {
		return original.hasNext();
	}

	@Override
	public R next() {
		return conversion.apply(original.next());
	}
	
	@Override
	public <U> Sequence<U> then(Function<R, U> conversion) {
		requireNonNull(conversion, "conversion");
		return new SequenceWithConversion<>(original, this.conversion.andThen(conversion));
	}

	@Override
	public void close() {
		original.close();
	}

}
