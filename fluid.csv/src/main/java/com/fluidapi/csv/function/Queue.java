package com.fluidapi.csv.function;

/**
 * A safely operated very simplistic queue.
 * 
 * @author Arindam Biswas
 * @param <T> any object type
 */
public interface Queue<T> {

	/**
	 * if not possible to add, just returns {@code false}, doesn't throw any error
	 * 
	 * @param t the next element to insert
	 * @return {@code true} if there was space in queue and that element is added to
	 *         queue, {@code false} if it could not be enqueued
	 */
	boolean enqueue(T t);

	/**
	 * if no element, just returns {@code null}, doesn't throw any exception
	 * 
	 * @return next element from the front, {@code null} if no element available or
	 *         the element itself is {@code null}
	 */
	T dequeue();
	
	/**
	 * @return number of elements currently in the queue
	 */
	int length();
	
	/**
	 * @return yes/no answer to the question - "does the queue has no elements in it?"
	 */
	boolean isEmpty();

}
