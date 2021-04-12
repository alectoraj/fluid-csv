package com.fluidapi.csv.internal.mapper.provider.beans;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.fluidapi.csv.exception.UncheckedException;
import com.fluidapi.csv.internal.function.Setter;
import com.fluidapi.csv.internal.function.Transformation;

public class FieldInfo extends MemberInfo<Field> {

	public FieldInfo(Field member) {
		super(member);
	}

	@Override
	public Setter findSetter() {
		try {
			return setUsingSetter();
		} catch (NoSuchMethodException | SecurityException e) {
			failIfMemberNotWritable(e);
			return setUsingOverwrite();
		}
	}

	private Setter setUsingOverwrite() {
		Setter setField = (instance, value) -> member.set(instance, value);
		return setField.with(typeTransformation());
	}
	
	// Not using MethodInfo since the behavior is different there | close though
	private Setter setUsingSetter() throws NoSuchMethodException, SecurityException {
		String fieldSetter = toSetterName();
		Method method = findMethod(fieldSetter, member.getType());
		
		failIfMethodNotAccessible(method);
		Setter callMethod = (instance, value) -> method.invoke(instance, value);
		
		return callMethod.with(typeTransformation());
	}

	private Method findMethod(String name, Class<?> type) throws NoSuchMethodException, SecurityException {
		return memberOf().getDeclaredMethod(name, type);
	}

	private String toSetterName() {
		
		// first construct as set + fieldName
		StringBuilder setter = new StringBuilder("set");
		setter.append(member.getName());
		
		// capitalize first letter of field name
		final int firstLetterIndex = 3;	// since "set" is already there
		
		// transform
		char firstLetter = setter.charAt(firstLetterIndex);
		firstLetter = Character.toUpperCase(firstLetter);
		setter.setCharAt(firstLetterIndex, firstLetter);
		
		// it's constructed,
		// return the string
		return setter.toString();
	}

	private void failIfMethodNotAccessible(Method method) throws NoSuchMethodException {
		if( !method.trySetAccessible() ) {
			throw new NoSuchMethodException(setterNotFoundMessage());
		}
	}

	private void failIfMemberNotWritable(Exception e) {
		if( !member.trySetAccessible() ) {
			throw new UncheckedException(setterNotFoundMessage(), e);
		}
	}
	
	private String setterNotFoundMessage() {
		return "a setter with name %s was not found or not accessible"
				.formatted(toSetterName());
	}

	private Transformation typeTransformation() {
		// TODO use standard methodology to convert input string into given type
		return Transformation.none();
	}

}
