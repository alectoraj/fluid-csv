package com.fluidapi.csv.reader.provider.bean;

import static com.fluidapi.csv.validaton.FailCheck.failIf;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.fluidapi.csv.exception.CsvException;

import lombok.NonNull;

// TODO change things into get operation
public class GetterInfo extends MethodInfo implements AutoGetter {

	final MemberInfo<?> origin;
	final TypeInfo<?> typeOrigin;
	
	// TODO initialize mapper
	
	public GetterInfo(@NonNull MethodInfo method) {
		super(method.it);
		
		failIf( !method.isGetter(), "method is not a getter" );
		origin = method;
		typeOrigin = new ClassInfo<>(method.returnType());
	}

	GetterInfo(@NonNull FieldInfo field, @NonNull Method getter) {
		super(getter);
		origin = field;
		typeOrigin = field;
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
		// TODO map to string by type & format
		return String.valueOf(instance);
	}

}
