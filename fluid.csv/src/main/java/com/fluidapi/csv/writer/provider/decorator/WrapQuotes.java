package com.fluidapi.csv.writer.provider.decorator;

import lombok.RequiredArgsConstructor;

/**
 * For performance & logical reasons, this decorator does not escape anything,
 * even the quote marks. it assumes they're either already escaped, or there can
 * be no character that needs escaping.
 * 
 * <p>
 * if escaping is required, refer {@link Escape}
 * </p>
 * 
 * @author Arindam Biswas
 * @since 1.2
 */
@RequiredArgsConstructor
public class WrapQuotes extends DecorateIndependently {

	final char quoteStart;
	final char quoteEnd;
	
	@Override
	public String decorate(String column) {
		return "%s%s%s".formatted(quoteStart, column, quoteEnd);
	}

}
