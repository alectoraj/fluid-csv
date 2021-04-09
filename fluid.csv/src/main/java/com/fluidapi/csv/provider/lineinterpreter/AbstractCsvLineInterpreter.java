package com.fluidapi.csv.provider.lineinterpreter;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import com.fluidapi.csv.CsvLineInterpreter;
import com.fluidapi.csv.bean.CsvInterpretedLine;

public abstract class AbstractCsvLineInterpreter implements CsvLineInterpreter {
	
	@Override
	public CsvInterpretedLine interpret(String line) {
		return isEmpty(line)
			 ? CsvInterpretedLine.empty()
			 : interpretSafe(line);
	}

	/**
	 * @param line is definitely not empty, interpret depending on this info
	 * @return implementation specific {@link CsvInterpretedLine}
	 */
	protected abstract CsvInterpretedLine interpretSafe(String line);
}
