package com.fluidapi.csv;

import java.util.function.Function;

@FunctionalInterface
public interface LineSplitter extends Function<String, String[]> {

}
