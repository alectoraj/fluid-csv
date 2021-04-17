package com.fluidapi.csv.reader.provider.bean;

import static com.fluidapi.csv.reader.CsvReader.pick;
import static com.fluidapi.csv.validaton.FailCheck.failIf;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.fluidapi.csv.annotations.CsvDeserializer;
import com.fluidapi.csv.exception.CsvException;
import com.fluidapi.csv.reader.CsvBeanDeserializer;
import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;
import com.fluidapi.csv.reader.provider.deserializer.column.ColumnDeserializers;

import lombok.NonNull;

public class SetterInfo extends MethodInfo implements AutoSetter {

	final MemberInfo<?> origin;
	final TypeInfo<?> typeOrigin;
	
	CsvBeanDeserializer<?> autoDeserializer;

	public SetterInfo(@NonNull MethodInfo methodInfo) {
		super(methodInfo.it);
		
		failIf( !methodInfo.isSetter(), "method %s is not a setter".formatted(methodInfo.it) );
		origin = methodInfo;
		typeOrigin = new ParameterInfo(it.getParameters()[0]); // since it's setter, it'll have exactly 1 parameter
		
		initialize();
	}

	SetterInfo(@NonNull FieldInfo field, @NonNull Method setter) {
		super(setter);
		origin = field;
		typeOrigin = field;
		
		initialize();
	}
	
	// constructor helper, hence right here, not below
	private void initialize() {
		CsvColumnMapper<?> autoMapper = hasCustomMapper(origin) ? findCustomMapper(origin) : null;
		autoDeserializer = pick(origin.getCsvColumnIndex(), ColumnDeserializers.of(typeOrigin, origin, autoMapper));
	}

	static boolean hasCustomMapper(@NonNull AnnotatedInfo<?> property) {
		return property.hasAnnotation(CsvDeserializer.class);
	}
	
	/**
	 * <p>
	 * <h2 style="color: #ea3210;">CAUTION!</h2>
	 * Not ensuring the presence of {@link CsvDeserializer}
	 * annotation may result in exceptions.
	 * </p>
	 * 
	 * @param property assumes to have {@link CsvDeserializer} annotation on it
	 * @return the custom {@link CsvColumnMapper}, constructed using the default
	 *         constructor of the class specified in {@link CsvDeserializer}
	 */
	static CsvColumnMapper<?> findCustomMapper(AnnotatedInfo<?> property) {
		
		CsvDeserializer csvDeserializer = property.findAnnotation(CsvDeserializer.class);
		ClassInfo<? extends CsvColumnMapper<?>> typeInfo = new ClassInfo<>(csvDeserializer.value());
		
		return typeInfo.defaultConstructor()
				.map( ConstructorInfo::construct )
				.orElseThrow(() -> new CsvException("CsvDeserializer.CsvColumnMapper.DefaultConstructor not found"));
	}

	/**
	 * call the method as a setter.
	 * 
	 * @param instance the instance to set it on
	 * @param value the value to be set
	 * @see #isSetter()
	 */
	@Override
	public void set(Object instance, Object value) {
		try {
			// call the single argument method
			it.invoke(instance, value);
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			
			// setter is not accessible
			throw new CsvException(getName()
					+ " not accessible or not compatible for parameter type "
					+ (value == null ? "null" : value.getClass()), e);
		}
	}
	

	/**
	 * picks the configured column as string input and automatically convert it to
	 * desired type, and sets the value
	 * 
	 * @param instance the object on which the setter to be called
	 * @param value    the set of columns to pick from
	 */
	@Override
	public void autoSet(Object instance, String[] columns) {
		set(instance, autoDeserializer.apply(columns));
	}

}
