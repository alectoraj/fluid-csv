package com.fluidapi.csv.function.provider;

import static com.fluidapi.csv.validator.Requires.requiresTrue;

import com.fluidapi.csv.function.LimitedQueue;
import com.fluidapi.csv.function.Queue;

/**
 * uses array as backbone,
 * and is <strong>not</strong> thread-safe
 * 
 * @author Arindam Biswas
 * @param <T> any object type
 */
public class FixedLengthQueue<T> implements Queue<T>, LimitedQueue<T> {
	
	/** fixed length space */
	protected final T[] queue;
	
	/** queue empties from front */
	protected int front;
	
	/** queue fills from rare */
	protected int rare;
	
	/** logs current count of elements */
	protected int length;
	
	@SuppressWarnings("unchecked")
	public FixedLengthQueue(int length) {
		requiresTrue(length > 0, "length must be non-zero positive");
		
		this.queue = (T[]) new Object[length];
		this.front = 0;
		this.rare = -1;
		this.length = 0;
	}

	@Override
	public boolean enqueue(T t) {
		
		// zero capacity left
		// return status
		if( isFull() ) {
			return false;
		}
		
		// update capacity before occupying
		// although we're not ensuring thread-safety
		// but it's best to optimize
		length++ ;
		
		// calculate next index
		// ensure circularity
		if( ++rare == queue.length ) {
			rare = 0;
		}

		// enqueue element and return status
		queue[rare] = t;
		return true;
	}

	@Override
	public T dequeue() {
		if( isEmpty() ) {	// no element available to be removed
			return null;
		}
		
		// fetch the next element from front
		// and detach it from this queue so it may be garbage collected
		T next = queue[front];
		queue[front] = null;
		length-- ; // real-time update capacity
		
		// ensure circularity
		if( ++front == queue.length ) {
			front = 0;
		}
		
		return next;
	}

	@Override
	public int length() {
		return length;
	}

	@Override
	public int remaining() {
		return queue.length - length;
	}
	
	@Override
	public boolean isEmpty() {
		return length == 0;
	}

	@Override
	public boolean isFull() {
		return length == queue.length;
	}
	
}
