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
 * @since 1.0
 */
public interface Cleanable {
	
	boolean isClean();
	void clean();
	
}
