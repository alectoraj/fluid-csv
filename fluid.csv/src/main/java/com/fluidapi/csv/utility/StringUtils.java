package com.fluidapi.csv.utility;

import static java.util.regex.Pattern.compile;
import static org.apache.commons.lang3.StringUtils.isBlank;;

public interface StringUtils {
	
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
