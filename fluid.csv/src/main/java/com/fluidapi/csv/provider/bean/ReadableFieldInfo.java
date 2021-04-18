package com.fluidapi.csv.provider.bean;

import static com.fluidapi.csv.validaton.FailCheck.failIf;

import java.lang.reflect.Field;

import com.fluidapi.csv.exception.CsvException;
import com.fluidapi.csv.writer.provider.serializer.column.ColumnSerializers;
import com.fluidapi.csv.writer.serializer.CsvBeanMapper;

import lombok.NonNull;

public class ReadableFieldInfo extends FieldInfo implements AutoGetter {
	
	CsvBeanMapper<Object> autoMapper;

	public ReadableFieldInfo(@NonNull FieldInfo info) {
		this(info.it);
	}
	
	public ReadableFieldInfo(@NonNull Field it) {
		super(it);

		failIf(!isCsvColumn(), "not a csv column field");
		failIf(!canAccess(), "not readable");
		
		autoMapper = ColumnSerializers.of(this, this);
	}

	@Override
	public Object get(Object instance) {
		try {
			// get the field value of the getter
			return it.get(instance);
			
		} catch (IllegalAccessException e) {
			
			// re-throw
			throw new CsvException("%s of type %s is not readable on %s"
					.formatted(getName(), getInstanceType(instance), instance), e);
		}
	}

	@Override
	public String autoGet(Object instance) {
		return autoMapper.apply(get(instance));
	}

	private Class<? extends Object> getInstanceType(Object instance) {
		return instance != null ? instance.getClass() : it.getDeclaringClass();
	}
}
