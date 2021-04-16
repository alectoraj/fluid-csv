package com.fluidapi.csv.reader.provider.deserializer.column;

import com.fluidapi.csv.function.MapConstructor;
import com.fluidapi.csv.reader.provider.bean.AnnotatedInfo;
import com.fluidapi.csv.reader.provider.bean.TypeInfo;
import com.fluidapi.csv.utility.MapSupport;

// TODO use enum converter and also test
// idea - extend MapSupport in-line, and over register, add is-enum condition
// let register stay though, so custom enum mappers e.g. java.time.Month can register themselves
public class MapEnum extends MapSafe<Object> {
	
	final Class<?> type;
	
	public MapEnum(TypeInfo<?> typeInfo) {
		type = typeInfo.getType();
	}
	
	@Override
	protected Object mapSafe(String column) {
		column = column.strip().toUpperCase();
		return findEnum(column);
	}

	private <E extends Enum<E>> E findEnum(String column) {
		@SuppressWarnings("unchecked")
		Class<E> enumType = (Class<E>) type;
		return Enum.valueOf(enumType, column);
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
				 : (T) new MapEnum(typeInfo);
		}
		
	}
}
