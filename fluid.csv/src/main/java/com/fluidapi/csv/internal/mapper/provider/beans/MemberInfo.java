package com.fluidapi.csv.internal.mapper.provider.beans;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;

import com.fluidapi.csv.internal.function.Setter;

public abstract class MemberInfo<T extends AccessibleObject & Member> extends AnnotatedInfo<T> {
	
	public MemberInfo(T member) {
		super(member);
	}
	
	protected Class<?> memberOf() {
		return member.getDeclaringClass();
	}
	
	public abstract Setter findSetter();
	
}
