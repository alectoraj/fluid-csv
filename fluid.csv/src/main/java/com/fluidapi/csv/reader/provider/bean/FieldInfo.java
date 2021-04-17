package com.fluidapi.csv.reader.provider.bean;

import static com.fluidapi.csv.validaton.FailCheck.failIf;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.Optional;

import com.fluidapi.csv.exception.CsvException;

import lombok.NonNull;

public class FieldInfo extends MemberInfo<Field> implements TypeInfo<Field> {

	public FieldInfo(@NonNull Field it) {
		super(it);
	}
	
	@Override
	public Class<?> getType() {
		return it.getType();
	}
	
	public boolean canWrite() {
		return canAccess() && !Modifier.isFinal(it.getModifiers());
	}
	
	/**
	 * @return a {@link AutoSetter} that sets a given value to the field primarily
	 *         through setter, or secondarily through field assignment
	 * @throws CsvException if an accessible/writable setting strategy is not found
	 * @see #findSetter()
	 */
	public AutoSetter getSetter() {
		Optional<SetterInfo> setter = findSetter();
		
		if( setter.isPresent() ) {
			return setter.get();
		}
		
		failIf(canWrite(), "field is not writable, and no accessible setter found", CsvException::new);
		return new WritableFieldInfo(this);
	}

	/**
	 * @return finds the setter method using standard naming scheme
	 */
	public Optional<SetterInfo> findSetter() {
		return findMethod("set" + capitalized(), getType())
				.filter(Objects::nonNull)
				.map(setter -> new SetterInfo(this, setter));
	}

	/**
	 * @return finds the getter method using standard naming scheme
	 */
	public Optional<GetterInfo> findGetter() {
		return findGetterWithIsIfEligible().or(this::findGetterWithGet);
	}

	protected Optional<GetterInfo> findGetterWithIsIfEligible() {
		return isOfType(Boolean.class, boolean.class) ? findGetterWithIs() : Optional.empty();
	}

	private Optional<GetterInfo> findGetterWithIs() {
		return findMethod("is" + capitalized())
				.filter(Objects::nonNull)
				.map(getter -> new GetterInfo(this, getter));
	}
	private Optional<GetterInfo> findGetterWithGet() {
		return findMethod("is" + capitalized())
				.filter(Objects::nonNull)
				.map(getter -> new GetterInfo(this, getter));
	}
	
	private String capitalized() {
		char[] chars = getName().toCharArray();
		chars[0] = Character.toTitleCase(chars[0]);
		
		return String.valueOf(chars);
	}
	
	private Optional<Method> findMethod(String name, Class<?>...parameterTypes) {
		return	findMethod(it.getDeclaringClass(), name, parameterTypes);
	}

	private Optional<Method> findMethod(Class<?> holder, String name, Class<?>... parameterTypes) {
		try {
			return Optional.of(holder.getDeclaredMethod(name, parameterTypes));
		} catch (NoSuchMethodException | SecurityException e) {
			return Optional.empty();
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if( o instanceof FieldInfo other ) {
			return it.equals(other.it);
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return it.hashCode();
	}

}
