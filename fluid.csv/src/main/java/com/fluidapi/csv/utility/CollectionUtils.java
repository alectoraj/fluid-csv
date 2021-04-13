package com.fluidapi.csv.utility;

import static com.fluidapi.csv.utility.StreamUtils.fastStream;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.function.IntPredicate;

/**
 * All sorts of {@link Collection} or {@code Array} related methods that are not
 * found in existing API's like {@code Apache Commons Lang3}
 * 
 * @author Arindam Biswas
 * @since 1.0
 */
public interface CollectionUtils {
	
	/**
	 * Checks if any item in the {@code int[]} contains an {@code int} that matches
	 * with the condition given in {@link IntPredicate}
	 * 
	 * @param ints      the array of {@code int} numbers
	 * @param condition the int check
	 * @return if any {@code int} in the array matches the given condition
	 */
	static boolean contains(int[] ints, IntPredicate condition) {
		requireNonNull(ints, "ints");
		requireNonNull(condition, "condition");
		return fastStream(ints).anyMatch(condition);
	}
	
}
