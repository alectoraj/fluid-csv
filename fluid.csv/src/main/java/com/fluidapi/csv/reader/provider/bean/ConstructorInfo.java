package com.fluidapi.csv.reader.provider.bean;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.fluidapi.csv.exception.CsvException;

import lombok.NonNull;

public class ConstructorInfo<T> extends ExecutableInfo<Constructor<T>> {

	public ConstructorInfo(@NonNull Constructor<T> it) {
		super(it);
	}
	
	public boolean isDefaultConstructor() {
		return isPublic() && isUnitArgCall();
	}
	
	public boolean isCsvConstructor() {
		return parameters()
				.allMatch(ParameterInfo::isCsvColumn);
	}
	
	public T construct(Object...args) {
		try {
			
			// call the constructor
			// checking the arguments compatibility will be done by reflection api
			return it.newInstance(args);
			
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			
			// re-throw the error, user should know about it
			throw new CsvException("error constructing object", e);
		}
	}

}
