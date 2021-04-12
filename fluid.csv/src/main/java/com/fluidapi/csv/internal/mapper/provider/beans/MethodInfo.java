package com.fluidapi.csv.internal.mapper.provider.beans;

import java.lang.reflect.Method;

import com.fluidapi.csv.exception.UncheckedException;
import com.fluidapi.csv.internal.function.Setter;

public class MethodInfo extends MemberInfo<Method> {

	public MethodInfo(Method member) {
		super(member);
	}
	
	@Override
	public Setter findSetter() {
		failIfMethodNotAccessible();
		
		Setter callMethod = (instance, value) -> member.invoke(instance, value);
		return callMethod.with(typeTransformation());
	}
	
	public boolean isFieldSetter() {
		return member.getParameterCount() == 1;
	}

	private void failIfMethodNotAccessible() {
		if( !member.trySetAccessible() ) {
			throw new UncheckedException(setterNotAccessibleMessage());
		}
	}

	private String setterNotAccessibleMessage() {
		return "method %s(%s) is not accessible"
				.formatted(member.getName(), firstParamTypeName());
	}

	private String firstParamTypeName() {
		return switch (member.getParameterCount()) {
			case 0 -> "";
			case 1 -> targetType().getSimpleName();
			default -> "too many parameters";
		};
	}

	@Override
	public Class<?> targetType() {
		// assumption: is a valid field setter
		return member.getParameterTypes()[0];
	}

}
