package com.fluidapi.csv.function;

/**
 * a queue that has a limitation on it's capacity
 * 
 * @author Arindam Biswas
 * @param <T> any object type
 */
public interface LimitedQueue<T> extends Queue<T> {
	
	/**
	 * @return capacity left in the queue
	 */
	int remaining();
	
	/**
	 * @return yes/no answer to the question - "is this queue full right now?"
	 */
	boolean isFull();
	
}
