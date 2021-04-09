package com.fluidapi.csv.function.provider;

import com.fluidapi.csv.function.Sequence;

public abstract class CloseOnceSequence<T> implements Sequence<T> {
	
	private boolean closed;
	
	public CloseOnceSequence() {
		closed = false;
	}

	@Override
	public final boolean hasNext() {
		if( closed ) {
			return false;
		}
		
		boolean hasMore = hasMoreElements();
		if( !hasMore ) {
			close();
		}
		
		return hasMore;
	}
	
	@Override
	public final T next() {
		if( closed ) {
			throw new IllegalStateException("sequence is closed");
		}
		
		return nextElement();
	}
	
	@Override
	public final void close() {
		if( !closed ) {
			closed = true;
			closeResources();
		}
	}
	
	protected boolean isClosed() {
		return closed;
	}

	protected abstract T nextElement();
	protected abstract boolean hasMoreElements();
	protected abstract void closeResources();

}
