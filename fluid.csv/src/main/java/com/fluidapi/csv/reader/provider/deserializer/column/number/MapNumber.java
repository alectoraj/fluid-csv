package com.fluidapi.csv.reader.provider.deserializer.column.number;

import com.fluidapi.csv.reader.provider.deserializer.column.MapSafe;
import com.fluidapi.csv.utility.ClassUtils;
import com.fluidapi.csv.utility.MapSupport;

public abstract class MapNumber<T extends Number> extends MapSafe<T> {
	
	/**
	 * {@link MapSupport#register(Class, com.fluidapi.csv.function.MapConstructor)}
	 * method to be only used by child classes to register support
	 */
	public final static MapSupport<MapNumber<?>> support = new MapSupport<>();
	
	static {
		ClassUtils.load(
				MapByte.class,
				MapShort.class,
				MapInteger.class,
				MapLong.class,
				MapFloat.class,
				MapDouble.class,
				MapBigInteger.class,
				MapBigDecimal.class,
				MapAtomicInteger.class,
				MapAtomicLong.class
		);
	}
	
}
