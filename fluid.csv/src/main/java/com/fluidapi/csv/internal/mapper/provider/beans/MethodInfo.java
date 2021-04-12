package com.fluidapi.csv.internal.mapper.provider.beans;

import java.lang.reflect.Method;

import com.fluidapi.csv.exception.UncheckedException;
import com.fluidapi.csv.internal.function.Setter;
import com.fluidapi.csv.internal.function.Transformation;

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
			case 1 -> member.getParameterTypes()[0].getSimpleName();
			default -> "too many parameters";
		};
	}

	private Transformation typeTransformation() {
		// TODO use standard methodology to convert input string into given type
		return Transformation.none();
	}

}
