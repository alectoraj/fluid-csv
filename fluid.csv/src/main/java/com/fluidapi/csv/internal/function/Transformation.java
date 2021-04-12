package com.fluidapi.csv.internal.function;

import static java.util.Objects.requireNonNull;

import java.util.function.UnaryOperator;

@FunctionalInterface
public interface Transformation extends UnaryOperator<Object> {
	
	Object transform(Object in);
	
	@Override
	default Object apply(Object t) {
		return transform(t);
	}

	/**
	 * @param before use the given transformation to compose the suitable input for
	 *               {@code this} transformation
	 * @return a combined {@link Transformation} that first applies {@code before}
	 *         and then {@code this}
	 */
	default Transformation with( Transformation before ) {
		requireNonNull(before, "before transformation");
		return in -> transform(before.transform(in));
	}
	
	/**
	 * @param after post {@code this} transformation, use given transformation to
	 *              further convert the value
	 * @return a combined {@link Transformation} that first applies {@code this} and
	 *         then {@code after}
	 */
	default Transformation then( Transformation after ) {
		requireNonNull(after, "post transformation");
		return in -> after.transform(transform(in));
	}
	
	/**
	 * A shorthand transformation method that does no conversion operation
	 * @return a transformation that does nothing
	 */
	static Transformation none() {
		return t -> t;
	}
	
}
