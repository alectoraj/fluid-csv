package com.fluidapi.csv.internal.mapper.provider.column;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fluidapi.csv.internal.mapper.provider.beans.MemberInfo;

public class LocalDateMapper extends AbstractTemporalMapper {
	
	public static final Class<LocalDate> supports = LocalDate.class;

	public LocalDateMapper(MemberInfo<?> memberInfo) {
		super(memberInfo);
	}
	
	@Override
	public Object transform(String in) {
		return LocalDate.parse(in, formatter);
	}

	@Override
	protected DateTimeFormatter defaultFormatter() {
		return DateTimeFormatter.ISO_LOCAL_DATE;
	}
	
}
