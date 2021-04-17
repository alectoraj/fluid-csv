package com.fluidapi.csv.provider.bean;

import static com.fluidapi.csv.provider.bean.SetterInfo.findCustomMapper;
import static com.fluidapi.csv.provider.bean.SetterInfo.hasCustomMapper;
import static com.fluidapi.csv.reader.CsvReader.pick;
import static com.fluidapi.csv.validaton.FailCheck.failIf;

import java.lang.reflect.Field;

import com.fluidapi.csv.exception.CsvException;
import com.fluidapi.csv.reader.CsvBeanDeserializer;
import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;
import com.fluidapi.csv.reader.provider.deserializer.column.ColumnDeserializers;

import lombok.NonNull;

public class WritableFieldInfo extends FieldInfo implements AutoSetter {
	
	CsvBeanDeserializer<?> autoDeserializer;

	public WritableFieldInfo(@NonNull FieldInfo info) {
		this(info.it);
	}
	
	public WritableFieldInfo(@NonNull Field it) {
		super(it);

		failIf(!isCsvColumn(), "not a csv column field");
		failIf(!canWrite(), "not writable");
		
		CsvColumnMapper<?> autoMapper = hasCustomMapper(this) ? findCustomMapper(this) : null;
		autoDeserializer = pick(getCsvColumnIndex(), ColumnDeserializers.of(this, this, autoMapper));
	}
	
	@Override
	public void set(Object instance, Object value) {
		try {
			// assign the value directly
			it.set(instance, value);
			
		} catch (IllegalArgumentException | IllegalAccessException e) {
			
			// setter is not accessible
			throw new CsvException(getName()
					+ " not accessible or not compatible for type "
					+ (value == null ? "null" : value.getClass()), e);
		}
	}
	
	@Override
	public void autoSet(Object instance, String[] columns) {
		set(instance, autoDeserializer.apply(columns));
	}

}
