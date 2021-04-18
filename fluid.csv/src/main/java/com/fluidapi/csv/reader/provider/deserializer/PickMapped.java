package com.fluidapi.csv.reader.provider.deserializer;

import static com.fluidapi.csv.validaton.FailCheck.failIf;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;

import com.fluidapi.csv.reader.CsvBeanDeserializer;
import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;

/**
 * Picks the said column by index, maps it using given mapper and returns. If
 * the set of columns doesn't have enough columns to pick the index,
 * {@code null} is returned immediately. However, if a column contains
 * {@code null}, it'll be up to the mapper to handle or fail.
 * 
 * <ul>
 * <li>the index must be a valid 0-based index, must not be negative.</li>
 * <li>a mapping must be provided</li>
 * </ul>
 * 
 * @author Arindam Biswas
 * @since 0.1
 * 
 * @param <T> any object type
 */
public class PickMapped<T> implements CsvBeanDeserializer<T> {
	
	private final int index;
	private final CsvColumnMapper<T> withMapping;
	
	public PickMapped(int index, CsvColumnMapper<T> withMapping) {
		requireNonNull(withMapping, "mapping");
		failIf(index < 0, "negative index");
		
		this.index = index;
		this.withMapping = withMapping;
	}

	@Override
	public T convert(String[] columns) {
		return isOutOfRange(columns) ? null
			 : withMapping.apply(columns[index]);
	}

	private boolean isOutOfRange(String[] columns) {
		return isEmpty(columns)
			&& index >= columns.length;
	}

}
