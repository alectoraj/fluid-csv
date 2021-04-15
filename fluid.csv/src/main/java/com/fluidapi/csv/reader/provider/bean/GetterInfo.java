package com.fluidapi.csv.reader.provider.bean;

import static com.fluidapi.csv.validaton.FailCheck.failIf;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

import com.fluidapi.csv.exception.CsvException;

import lombok.NonNull;

public class GetterInfo extends MethodInfo {
	
	protected AnnotatedInfo<? extends Member> origin;

	public GetterInfo(@NonNull MethodInfo method) {
		super(method.it);
		
		failIf( !method.isGetter(), "method is not a getter" );
		origin = method;
	}

	GetterInfo(@NonNull FieldInfo field, @NonNull Method getter) {
		super(getter);
		origin = field;
	}
	
	/**
	 * call the method as a setter.
	 * 
	 * @param instance the instance to set it on
	 * @param value the value to be set
	 * @see #isSetter()
	 */
	public void set(Object instance, Object value) {
		try {
			// call the single argument method
			it.invoke(instance, value);
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			
			// setter is not accessible
			throw new CsvException(getName() + " not accessible", e);
		}
	}
	
	/**
	 * automatically convert the value as string input and convert it to the desired
	 * instance
	 * 
	 * @param instance the object on which the setter to be called
	 * @param value    the string value to be converted
	 */
	public void setAuto(Object instance, String value) {
		set(instance, autoConvert(value));
	}
	
	private Object autoConvert(String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
