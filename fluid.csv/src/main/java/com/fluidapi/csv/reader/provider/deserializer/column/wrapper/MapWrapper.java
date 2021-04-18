package com.fluidapi.csv.reader.provider.deserializer.column.wrapper;

import com.fluidapi.csv.reader.provider.deserializer.column.MapSafe;
import com.fluidapi.csv.reader.provider.deserializer.column.number.MapNumber;
import com.fluidapi.csv.utility.ClassUtils;
import com.fluidapi.csv.utility.MapSupport;

/**
 * @author Arindam Biswas
 * @since 0.1
 * @param <T> wrapper types, except sub class of {@link Number}, they're
 *            supported by {@link MapNumber}
 */
public abstract class MapWrapper<T> extends MapSafe<T> {
	
	/**
	 * {@link MapSupport#register(Class, com.fluidapi.csv.function.MapConstructor)}
	 * method to be only used by child classes to register support
	 */
	public final static MapSupport<MapWrapper<?>> support = new MapSupport<>();

	static {
		ClassUtils.load(
				MapBoolean.class,
				MapCharacter.class
		);
	}
}
