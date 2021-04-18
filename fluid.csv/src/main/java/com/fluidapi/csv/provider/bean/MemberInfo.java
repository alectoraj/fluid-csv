package com.fluidapi.csv.provider.bean;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

import com.fluidapi.csv.annotations.ReadOnly;

import lombok.NonNull;

public class MemberInfo<T extends AccessibleObject & Member> extends AnnotatedInfo<T> {

	public MemberInfo(@NonNull T it) {
		super(it);
	}

	/**
	 * if a member is public, definitely you can access it, unless restricted by
	 * module, in that case you can possibly never access them
	 */
	public boolean isPublic() {
		return Modifier.isPublic(it.getModifiers());
	}
	
	/**
	 * if not accessible already, tries to make it accessible and then returns the
	 * accessible status
	 */
	public boolean canAccess() {
		return it.trySetAccessible();
	}
	
	public String getName() {
		return it.getName();
	}
	
	/**
	 * @return whether the column is requested to be read-only column
	 */
	public boolean isReadOnly() {
		return hasAnnotation(ReadOnly.class);
	}
	
	/**
	 * A lambda usable shorthand for not-{@link #isReadOnly()}
	 * 
	 * @return whether the column is not requested to be read-only column
	 */
	public boolean isNotReadOnly() {
		return !isReadOnly();
	}

}
