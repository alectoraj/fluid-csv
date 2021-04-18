package com.fluidapi.csv.reader.provider.deserializer.column.enums;

import static com.fluidapi.csv.validaton.FailCheck.failIf;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toConcurrentMap;

import java.util.Map;
import java.util.function.Function;

import com.fluidapi.csv.function.MapConstructor;
import com.fluidapi.csv.provider.bean.AnnotatedInfo;
import com.fluidapi.csv.provider.bean.TypeInfo;
import com.fluidapi.csv.reader.provider.deserializer.column.MapSafe;
import com.fluidapi.csv.utility.ClassUtils;
import com.fluidapi.csv.utility.MapSupport;

public class MapEnum extends MapSafe<Enum<?>> {
	
	protected final Class<?> type;
	protected final Map<String, Enum<?>> enumMap;
	
	public MapEnum(TypeInfo<?> typeInfo, AnnotatedInfo<?> property) {
		type = typeInfo.getType();
		failIf( !type.isEnum(), "only enum are supported" );
		
		enumMap = stream(type.getEnumConstants())
				.map(t -> (Enum<?>) t) // cast to enum
				.collect(toConcurrentMap(toEnumString(), t -> t));
	}

	@Override
	protected Enum<?> mapSafe(String column) {
		return enumMap.get(prepareKey(column));
	}
	
	protected String prepareKey(String column) {
		return column.toUpperCase();
	}

	private Function<Enum<?>, String> toEnumString() {
		Function<Enum<?>, String> enumString = Object::toString;
		enumString = enumString.andThen(String::toUpperCase);
		
		return enumString;
	}

	public static final MapSupport<MapEnum> support = new MapSupportUniversal<>();

	private static class MapSupportUniversal<T extends MapEnum> extends MapSupport<T> {
		
		@Override
		public boolean supports(Class<?> type) {
			return type.isEnum();
		}
		
		@Override
		@SuppressWarnings("unchecked")
		public T of(TypeInfo<?> typeInfo, AnnotatedInfo<?> origin) {
			MapConstructor<T> constructor = supportMap.get(typeInfo.getType());
			
			return constructor != null
				 ? constructor.apply(typeInfo, origin)
				 : (T) new MapEnum(typeInfo, origin);
		}
		
	}
	
	static {
		ClassUtils.load(
			MapMonth.class
		);
	}
}
