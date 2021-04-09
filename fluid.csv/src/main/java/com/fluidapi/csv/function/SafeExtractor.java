package com.fluidapi.csv.function;

/**
 * @author Arindam Biswas
 *
 * @param <T> a collection type, that holds elements of type {@code U}
 * @param <U> type of elements being held by the collection
 */
public interface SafeExtractor<T, U> {

	/**
	 * @param index location of the unit
	 * @return the element at given index, {@code null} if the index is invalid or
	 *         the element itself is {@code null} at said {@code index}
	 */
	U at(int index);

	/**
	 * @param startIndex [inclusive] the index from which to slice
	 * @param endIndex   [exclusive] the index before which to stop
	 * @return a slice of the original, specified by given indexes, {@code null} if
	 *         indexes are invalid
	 */
	T slice(int startIndex, int endIndex);

	/**
	 * @param fromIndex the index from which to take the portion
	 * @param length    the maximum number of content to be taken
	 * @return a part of the original content, specified by given index & length,
	 *         {@code null} if either or both of index and length are invalid
	 */
	T portion(int fromIndex, int length);

}
