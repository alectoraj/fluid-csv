package com.fluidapi.csv.reader.provider.bean;

import static java.util.Arrays.stream;

import java.lang.reflect.Executable;
import java.util.stream.Stream;

import lombok.NonNull;

public abstract class ExecutableInfo<T extends Executable> extends MemberInfo<T> {

	public ExecutableInfo(@NonNull T it) {
		super(it);
	}
	
	public int getParameterCount() {
		return it.getParameterCount();
	}

	public boolean isNoArgCall() {
		return isNArgCall(0);
	}
	public boolean isUnitArgCall() {
		return isNArgCall(1);
	}
	public boolean isNArgCall(int n) {
		return getParameterCount() == n;
	}
	
	public Stream<ParameterInfo> parameters() {
		return stream(it.getParameters())
				.map(ParameterInfo::new);
	}

}
