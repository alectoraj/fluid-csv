package com.fluidapi.csv.reader.provider.deserializer.column.primitive;

import com.fluidapi.csv.reader.provider.deserializer.column.MapSafe;
import com.fluidapi.csv.utility.ClassUtils;
import com.fluidapi.csv.utility.MapSupport;

public abstract class MapPrimitive<T> extends MapSafe<T> {
	
	/**
	 * {@link MapSupport#register(Class, com.fluidapi.csv.function.MapConstructor)}
	 * method to be only used by child classes to register support
	 */
	public final static MapSupport<MapPrimitive<?>> support = new MapSupport<>();

	static {
		ClassUtils.load(
				MapIntPrimitive.class
		);
	}
}
