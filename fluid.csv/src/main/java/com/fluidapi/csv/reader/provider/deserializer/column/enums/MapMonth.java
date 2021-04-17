package com.fluidapi.csv.reader.provider.deserializer.column.enums;

import static java.util.Arrays.stream;

import java.time.Month;

import com.fluidapi.csv.provider.bean.AnnotatedInfo;
import com.fluidapi.csv.provider.bean.TypeInfo;

public class MapMonth extends MapEnum {

	static final char zero = '0';
	static final char zeroIndex = 0;
	static final char numberIndex = 1;
	
	public MapMonth(TypeInfo<?> typeInfo, AnnotatedInfo<?> property) {
		super(typeInfo, property);

		// populate index & three-letter names
		stream(Month.values())
		.forEach(month -> {
			enumMap.put(Integer.toString(month.getValue()), month);
			enumMap.put(month.toString().substring(0, 3), month);
		});
	}
	
	@Override
	protected String prepareKey(String column) {
		return column.charAt(zeroIndex) == zero
			 ? String.valueOf(column.charAt(numberIndex))
			 : super.prepareKey(column);
	}
	
	static {
		support.register(Month.class, MapMonth::new);
	}

}
