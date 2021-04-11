package com.fluidapi.csv;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface OrmMapper<T> extends Function<String[], T> {

	default <V> OrmMapper<V> then(Function<? super T, ? extends V> after) {
        Objects.requireNonNull(after);
        return (String[] columns) -> after.apply(apply(columns));
    }
	
}
