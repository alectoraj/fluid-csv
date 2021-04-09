package com.fluidapi.csv;

import static com.fluidapi.csv.validator.Requires.requiresTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.util.function.Consumer;

import com.fluidapi.csv.provider.BufferedCsvLineReader;
import com.fluidapi.csv.provider.contentreaders.ReadEntireFile;
import com.fluidapi.csv.provider.contentreaders.ReadLastLines;
import com.fluidapi.csv.provider.contentreaders.ReadTopLines;
import com.fluidapi.csv.provider.lineinterpreter.NoSplitInterpreter;
import com.fluidapi.csv.provider.lineinterpreter.SplitByFixedLength;
import com.fluidapi.csv.provider.lineinterpreter.SplitUsingDelimiter;
import com.fluidapi.csv.provider.rowmapper.PickStringColumn;

public class Csv {

	// ENTRY POINT //

	public static CsvLineReader using(CsvLineInterpreter lineInterpreter) {
		return new BufferedCsvLineReader(lineInterpreter);
	}

	// LINE SPLITTERS //

	public static CsvLineInterpreter delimiter(String delimiter) {
		return new SplitUsingDelimiter(delimiter);
	}

	public static CsvLineInterpreter fixed(int... lengths) {
		return new SplitByFixedLength(lengths);
	}

	public static CsvLineInterpreter line() {
		return new NoSplitInterpreter();
	}

	// CONTENT OR LINES READER //
	
	/**
	 * @return a preprocessor that does nothing, since reader is good to go
	 */
	public static Consumer<BufferedReader> readerOk() {
		return reader -> {};
	}
	
	/**
	 * @param lines must be positive, "0" redirects to {@link #readerOk()}
	 * @return {@link BufferedReader} preprocessor
	 */
	public static Consumer<BufferedReader> skip(int lines) {
		requiresTrue(lines >= 0, "lines cannot be negative");
		return lines == 0 ? readerOk()
			 : reader -> {
				 try {
					 int lineCount = lines;
					 while( --lineCount >= 0 && reader.readLine() != null );
				 } catch( IOException e ) {
					 throw new UncheckedIOException(e);
				 }
			 };
	}

	/**
	 * Calls {@link #lines(Consumer)} with no preprocessing
	 * @see #lines(Consumer)
	 */
	public static CsvContentReader lines() {
		return lines(readerOk());
	}

	/**
	 * Calls {@link #head(int, Consumer)} with no preprocessing
	 * @see #head(int, Consumer)
	 */
	public static CsvContentReader head(int lines) {
		return head(lines, readerOk());
	}

	/**
	 * Calls {@link #tail(int, Consumer)} with no preprocessing
	 * @see #tail(int, Consumer)
	 */
	public static CsvContentReader tail(int lines) {
		return tail(lines, readerOk());
	}

	/**
	 * Reads entire file, line-by-line
	 * 
	 * @param filePreProcessor any preprocessing before starts reading the file, e.g. {@link #skip(int)}
	 * @return {@link CsvContentReader} that reads entire file
	 */
	public static CsvContentReader lines(Consumer<BufferedReader> filePreProcessor) {
		return new ReadEntireFile(filePreProcessor);
	}

	/**
	 * tries to imitate the head command in Linux
	 * 
	 * @param lines            specifies the number of lines to be read from top
	 *                         <ul>
	 *                         <li>if positive - reads n lines from top</li>
	 *                         <li>if negative - reads lines from top, except last n lines</li>
	 *                         </ul>
	 * @param filePreProcessor any preprocessing before starts reading the file,
	 *                         e.g. {@link #skip(int)}
	 * @return {@link CsvContentReader} that reads specified number of lines from
	 *         top
	 * @see ReadTopLines
	 * @see #head(int)
	 */
	public static CsvContentReader head(int lines, Consumer<BufferedReader> filePreProcessor) {
		return new ReadTopLines(lines, filePreProcessor);
	}

	/**
	 * tries to imitate the tail command in Linux
	 * 
	 * @param lines            specifies the number of lines to be read from bottom,
	 *                         the sequence is still top-to-bottom
	 *                         <ul>
	 *                         <li>if positive - reads last n lines at the bottom</li>
	 *                         <li>if negative - reads all lines from bottom, except top n lines</li>
	 *                         </ul>
	 * @param filePreProcessor any preprocessing before starts reading the file,
	 *                         e.g. {@link #skip(int)}
	 * @return {@link CsvContentReader} that reads specified number of lines from
	 *         top
	 * @see ReadTopLines
	 * @see #head(int)
	 */
	public static CsvContentReader tail(int lines, Consumer<BufferedReader> filePreProcessor) {
		return ReadLastLines.of(lines, filePreProcessor);
	}

	// ROW TO OBJECT MAPPERS //

	public static CsvRowMapper<String> string() {
		return string(0);
	}

	public static CsvRowMapper<String> string(int index) {
		return new PickStringColumn(index);
	}

	public static CsvRowMapper<Integer> integer() {
		return integer(0);
	}

	public static CsvRowMapper<Integer> integer(int index) {
		return string(index).then(String::strip).then(Integer::valueOf);
	}

	public static CsvRowMapper<BigDecimal> bigDecimal() {
		return bigDecimal(0);
	}

	public static CsvRowMapper<BigDecimal> bigDecimal(int index) {
		return string(index).then(String::strip).then(BigDecimal::new);
	}

	public static <T> CsvRowMapper<T> orm(Class<T> type) {
		return null;
	}

}
