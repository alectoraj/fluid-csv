package com.fluidapi.csv.writer.provider.decorator;

import static com.fluidapi.csv.utility.CollectionUtils.asSet;

import java.util.Set;

import lombok.RequiredArgsConstructor;

/**
 * For speed reasons, this decorator does not escape anything, even the quote marks.
 * it assumes they're either already escaped, or there can be no character that needs escaping.
 * 
 * <p>
 * if escaping is required,
 * </p>
 * 
 * @author Arindam Biswas
 * @since 1.2
 */
@RequiredArgsConstructor
public class Escape extends DecorateIndependently {

	final char escapeWith;
	final Set<Character> escapeThem;
	
	public Escape(char escapeWith, char...characters) {
		this(escapeWith, asSet(characters));
	}
	
	@Override
	protected String decorate(String column) {
		
		// prepare destination
		StringBuilder escaped = new StringBuilder(estimateOver(column));
		
		// append with escape
		for (char character : column.toCharArray()) {
			if( escapeThem.contains(character) ) {
				escaped.append(escapeWith);
			}
			
			escaped.append(character);
		}
		
		// return escaped string
		return escaped.toString();
	}

	private int estimateOver(String column) {
		return estimateOver(column.length());
	}

	private int estimateOver(int length) {
		// this is ~10% extra
		return length + length / 10;
	}

}
