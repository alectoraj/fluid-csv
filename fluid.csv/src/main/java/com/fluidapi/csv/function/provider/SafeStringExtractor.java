package com.fluidapi.csv.function.provider;

import static com.fluidapi.csv.utils.Utility.between;

import com.fluidapi.csv.function.SafeExtractor;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class SafeStringExtractor implements SafeExtractor<String, Character> {
	
	protected final String content;
	protected final int lastIndex;
	
	public SafeStringExtractor(String string) {
		this(string, string.length() - 1);
	}

	@Override
	public Character at(int index) {
		return isInvalid(index) ? null
			 : content.charAt(index);
	}

	@Override
	public String slice(int startIndex, int endIndex) {
		if( startIndex < 0 ) {		// if start is before 0
			if( endIndex < 0 ) {	// and end is before 0 too
				return null;		// then requested slice doesn't exist
			}
									// but if end is a valid or beyond max
			startIndex = 0;			// adjust start to take it from 0
		}
		
										// if start is already within range from lower end
		if( startIndex > endIndex ) {	// and still managed to be ahead of end
			return null;				// then requested slice doesn't exist
		}
		
		if( startIndex > lastIndex ) {	// if start is beyond max
			return null;				// then requested slice doesn't exist
		}
		
												// if start is within range
		if( endIndex > content.length() ) {		// but end is beyond max
			endIndex = content.length();		// then adjust end to take it only until last element
		}
		
		// at this point, start and end are only within range
		// and valid against each other
		// so it's safe to pick the actual content slice using them
		return content.substring(startIndex, endIndex);
	}

	@Override
	public String portion(int fromIndex, int length) {
		// even if start is below 0,
		// length might manage to include some real values
		// hence instead of recreating, just use using the slice
		return slice( fromIndex, fromIndex + length );
	}

	/**
	 * @param index the index to verify against content's length
	 * @return {@code true} if index is valid with given content's context
	 */
	private boolean isInvalid(int index) {
		return !between(0, index, lastIndex);
	}

}
