package com.fluidapi.csv.utility;

import static java.util.regex.Pattern.compile;
import static org.apache.commons.lang3.StringUtils.isBlank;;

public interface StringUtils {
	
	/**
	 * every word individually will be title cased.
	 * <p>
	 * every consecutive set of upper or lower case letters, numbers and underscore
	 * are treated as a single word. any other character, like space, line break,
	 * single or double quote, any symbol are treated as a word break
	 * </p>
	 * 
	 * @param string the string to be title cased, maybe null/empty/blank, but it'll
	 *               be returned as it is
	 * @return the title cased string
	 */
	static String toTitleCase(String string) {
		if( isBlank(string) ) return string;
		
		return compile("(\\w+)")
				.matcher(string)
				.replaceAll(match -> {
					char[] word = match.group(1).toCharArray();
					
					word[0] = Character.toTitleCase(word[0]);
					for (int index = 1; index < word.length; index++) {
						word[index] = Character.toLowerCase(word[index]);
					}
					
					return new String(word);
				});
	}
	
}
