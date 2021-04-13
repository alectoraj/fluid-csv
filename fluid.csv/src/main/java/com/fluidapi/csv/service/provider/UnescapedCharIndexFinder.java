package com.fluidapi.csv.service.provider;

import static com.fluidapi.csv.bean.Quote.NULL;
import static java.util.Objects.requireNonNull;

import com.fluidapi.csv.service.CharIndexFinder;
import com.fluidapi.csv.service.Cleanable;

/**
 * Finds the occurrence of asked character in the given string, from
 * <em>after</em> the last returned index. Any occurrence of asked character
 * prefixed with the configured escape character would be treated as a mismatch
 * and would not be treated as a eureka moment.
 * 
 * @author Arindam Biswas
 * @since 1.0
 */
public final class UnescapedCharIndexFinder implements CharIndexFinder, Cleanable {
	
	private char[] origin;
	private char escape;
	private int index;
	
	public UnescapedCharIndexFinder(String string, char escape) {
		requireNonNull(string, "string");
		this.origin = string.toCharArray();
		this.escape = escape;
		this.index = -1;
	}

	@Override
	public int findNext(char character) {
		
		if( isClean() ) {
			return failResponse;
		}
		
		// maintaining previous char, starting with invalid escape character
		// since (index - 1) may result in seeing escape character which might itself be escaped,
		// but we won't know about it, and knowing it would make the code very complex
		
		char previous = NULL;
		while( ++index < origin.length ) {
			
			// if character found, unescaped, return index
			if( origin[index] == character
				&& previous != escape ) {
				
				return index;
			}
			
			// before continuing, update states
			previous = origin[index];
		}
		
		// if it has come to fail response
		// then surely we've exhausted the origin entirely
		clean();
		
		return failResponse;
	}

	@Override
	public boolean isClean() {
		return origin == null;
	}

	@Override
	public void clean() {
		origin = null;
		escape = NULL;
		index = 0;
	}

}
