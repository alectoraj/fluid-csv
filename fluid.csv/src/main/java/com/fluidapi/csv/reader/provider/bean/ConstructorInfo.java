package com.fluidapi.csv.reader.provider.bean;

import java.lang.reflect.Constructor;

import lombok.NonNull;

public class ConstructorInfo extends ExecutableInfo<Constructor<?>> {

	public ConstructorInfo(@NonNull Constructor<?> it) {
		super(it);
	}
	
	public boolean isDefaultConstructor() {
		return isPublic() && isUnitArgCall();
	}
	
	public boolean isCsvConstructor() {
		return parameters()
				.allMatch(ParameterInfo::isCsvColumn);
	}

}
