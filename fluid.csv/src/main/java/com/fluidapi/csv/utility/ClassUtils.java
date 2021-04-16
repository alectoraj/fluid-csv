package com.fluidapi.csv.utility;

import static com.fluidapi.csv.utility.StreamUtils.fastStream;

import com.fluidapi.csv.exception.CsvException;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ClassUtils {

	public static void load(Class<?>...classes) {
		fastStream(classes)
		.forEach(ClassUtils::load);
	}

	public static void load(Class<?> classType) {
		load(classType.getCanonicalName());
	}
	
	public static void load(String classQualifier) {
		try {
			Class.forName(classQualifier);
		} catch (ClassNotFoundException e) {
			throw new CsvException("unable to load class " + classQualifier, e);
		}
	}
	
}
