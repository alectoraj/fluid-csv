package com.fluidapi.csv.provider.bean;

import static com.fluidapi.csv.validaton.FailCheck.failIf;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.fluidapi.csv.exception.CsvException;
import com.fluidapi.csv.writer.provider.serializer.column.ColumnSerializers;
import com.fluidapi.csv.writer.serializer.CsvBeanMapper;

import lombok.NonNull;

public class GetterInfo extends MethodInfo implements AutoGetter {

	final MemberInfo<?> origin;
	final TypeInfo<?> typeOrigin;
	
	CsvBeanMapper<Object> autoMapper;
	
	public GetterInfo(@NonNull MethodInfo method) {
		super(method.it);
		
		failIf( !method.isGetter(), "method is not a getter" );
		origin = method;
		typeOrigin = new ClassInfo<>(method.returnType());
		
		initialize();
	}

	GetterInfo(@NonNull FieldInfo field, @NonNull Method getter) {
		super(getter);
		
		origin = field;
		typeOrigin = field;
		
		initialize();
	}
	
	private void initialize() {
		autoMapper = ColumnSerializers.of(typeOrigin, origin);
	}

	@Override
	public Object get(Object instance) {
		try {
			
			// call the getter
			return it.invoke(instance);
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			
			// getter is not accessible
			throw new CsvException(getName() + " not accessible", e);
		}
	}

	@Override
	public String autoGet(Object instance) {
		return autoMapper.apply(get(instance));
	}

}
