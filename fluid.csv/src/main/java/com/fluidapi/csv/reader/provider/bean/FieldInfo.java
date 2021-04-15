package com.fluidapi.csv.reader.provider.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import lombok.NonNull;

public class FieldInfo extends MemberInfo<Field> implements TypeInfo<Field> {

	public FieldInfo(@NonNull Field it) {
		super(it);
	}
	
	@Override
	public Class<?> getType() {
		return it.getType();
	}

	public MethodInfo findGetter() {
		String prefix = isOfType(Boolean.class, boolean.class) ? "is" : "get";
		return findMethod(prefix + capitalized());
	}

	public MethodInfo findSetter() {
		return findMethod("set" + capitalized(), getType());
	}
	
	private String capitalized() {
		char[] chars = getName().toCharArray();
		chars[0] = Character.toTitleCase(chars[0]);
		
		return String.valueOf(chars);
	}
	
	private MethodInfo findMethod(String name, Class<?>...parameterTypes) {
		return new MethodInfo(
				findMethodOrNull(name,
						it.getDeclaringClass(),
						parameterTypes));
	}

	private Method findMethodOrNull(String name, Class<?> holder, Class<?>... parameterTypes) {
		try {
			return holder.getDeclaredMethod(name, parameterTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			return null;
		}
	}

}
