package com.fluidapi.csv.service.provider;

import static java.lang.Math.min;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import com.fluidapi.csv.service.Cleanable;
import com.fluidapi.csv.service.SafeExtractor;

/**
 * A {@link SafeExtractor} for String.
 * 
 * <p>
 * Maintains state, and call to {@link #next(int)} starts from <em>right
 * after</em> where the last call left it with. Which means, first ever call to
 * {@link #next(int)} starts from the beginning of given {@link String}.
 * </p>
 * 
 * @author Arindam Biswas
 * @since 0.1
 */
public final class StringExtractor implements SafeExtractor<String>, Cleanable {
	
	private String origin;
	private int index;
	
	public StringExtractor(String origin) {
		requireNonNull(origin, "origin");
		this.origin = origin;
		this.index = 0;
	}

	@Override
	public String next(int length) {
		// if everything is already extracted,
		// avoid excess processing
		if( isClean() ) {
			return EMPTY;
		}
		
		// extract next segment
		int endIndex = min(index + length, origin.length());
		String next = origin.substring(index, endIndex);
		
		// update states for next call
		updateIndex(endIndex);
		
		return next;
	}

	@Override
	public boolean isClean() {
		return index == origin.length();
	}
	
	@Override
	public void clean() {
		origin = EMPTY;
		index = origin.length();
	}
	
	private void updateIndex(int index) {
		if( index == origin.length() ) {
			clean();
		} else {
			this.index = index;
		}
	}

}
