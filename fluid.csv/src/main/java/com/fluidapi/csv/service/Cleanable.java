package com.fluidapi.csv.service;

import java.io.Closeable;

/**
 * Exposes methods to clean an object.
 * <p>
 * Although similar to {@link Closeable} or {@link AutoCloseable}, this is not
 * connected to them, and thus is not recognized as resource by Java, avoiding
 * warnings
 * </p>
 * 
 * @author Arindam Biswas
 * @since 0.1
 */
public interface Cleanable {
	
	/**
	 * @return if already cleaned
	 */
	boolean isClean();
	
	/**
	 * perform cleaning activity.
	 * should happen only once.
	 */
	void clean();
	
}
