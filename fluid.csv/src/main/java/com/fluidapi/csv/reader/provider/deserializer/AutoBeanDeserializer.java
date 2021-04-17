package com.fluidapi.csv.reader.provider.deserializer;

import static com.fluidapi.csv.bean.ValidateDuring.ALWAYS;
import static com.fluidapi.csv.bean.ValidateDuring.DESERIALIZATION;

import java.time.Year;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.hibernate.validator.HibernateValidator;

import com.fluidapi.csv.annotations.CsvColumn;
import com.fluidapi.csv.annotations.CsvDeserializer;
import com.fluidapi.csv.annotations.CsvStrip;
import com.fluidapi.csv.annotations.CsvTrim;
import com.fluidapi.csv.annotations.CsvValidate;
import com.fluidapi.csv.exception.CsvException;
import com.fluidapi.csv.provider.bean.AutoSetter;
import com.fluidapi.csv.provider.bean.ConstructorInfo;
import com.fluidapi.csv.provider.bean.CsvClassInfo;
import com.fluidapi.csv.provider.bean.FieldInfo;
import com.fluidapi.csv.provider.bean.SetterInfo;
import com.fluidapi.csv.reader.CsvBeanDeserializer;
import com.fluidapi.csv.reader.deserializer.CsvColumnMapper;
import com.fluidapi.csv.validaton.BeanValidation;

import jakarta.validation.Valid;

/**
 * Automatically maps all fields as configured. here's a few rules one need to
 * follow as well as few flexibilities one may utilize -
 * 
 * <p>
 * <h3>RULES</h3>
 * <ul>
 * <li>Must expose the default constructor - used to create the constructor</li>
 * <li>{@link CsvColumn @CsvColumn} must be provided with a 0-based index</li>
 * <li>if used on a method, it should be a setter, i.e.
 * <ul>
 * <li>single parameter method</li>
 * <li>return type as {@code void}</li>
 * <li>follows a setter's naming scheme -
 * <q>the prefix "set" followed by a UpperCamelCase text</q></li>
 * </ul>
 * </li>
 * <li>does not support nested/embedded types by default. you'll have to write a
 * custom type for this purpose (check flexibilities)</li>
 * </ul>
 * </p>
 * 
 * <p>
 * <h3>FLEXIBILITIES</h3>
 * <ul>
 * <li>If a type is not supported by default, you can always write your own
 * {@link CsvColumnMapper} and configure it to be used instead, using
 * {@link CsvDeserializer @CsvDeserializer} annotation</li>
 * <li>Preprocessors like {@link CsvStrip} or {@link CsvTrim} will always be
 * executed even if you're using a custom {@link CsvDeserializer}</li>
 * <li>{@link CsvColumn @CsvColumn} can be used on more than one field and
 * setter, and all will be populated with the said column, and each can have
 * their own compatible data type. i.e. you can map a 4-digit number to all of a
 * {@link String} field, a {@link Number} field, and a {@link Year} field, and
 * all will be populated separately</li>
 * <li>Thanks to this, you may avoid using {@link CsvDeserializer} with a
 * separate class for simpler types and just have a setter with {@link String}
 * or a supported argument and construct your custom type right in the
 * setter</li>
 * <li>You can use {@code javax.validation} annotations and they'll be validated
 * using {@link HibernateValidator}</li>
 * </ul>
 * </p>
 * 
 * @author Arindam Biswas
 * @see 1.0
 * @param <T> any bean type, must have a default constructor
 */
public class AutoBeanDeserializer<T> implements CsvBeanDeserializer<T> {

	private final Supplier<T> constructor;
	private final BiConsumer<T, String[]> populate;
	private final Consumer<T> validator;
	
	public AutoBeanDeserializer(Class<T> type) {
		// try setting type as accessible
		// find constructor, and try setting it accessible
		// find setters, try setting them as accessible
		// if proper has no accessible setter, try using assignment
		
		CsvClassInfo<T> classInfo = new CsvClassInfo<>(type);
		constructor = new BeanConstructor<>(classInfo);
		populate = new BeanFieldUpdater<>(classInfo);
		
		validator = toValidator(classInfo);
	}

	@Override
	public T convert(String[] columns) {
		
		// get and populate
		T instance = constructor.get();
		populate.accept(instance, columns);
		
		// validate bean, if configured
		validator.accept(instance);
		
		// return populated & validated bean
		return instance;
	}

	private Consumer<T> toValidator(CsvClassInfo<T> classInfo) {
		
		// if @Valid is added
		return classInfo.hasAnnotation(Valid.class)
		
		// or @CsvValidate with DESERIALIZATION or ALWAYS
			|| (classInfo.hasAnnotation(CsvValidate.class)
				&& classInfo.findAnnotation(CsvValidate.class).value()
					.isOneOf(ALWAYS, DESERIALIZATION))
			
		// then result a validator, or keep it no-op
			? BeanValidation::validate : t -> {};
	}
	
	static class BeanConstructor<T> implements Supplier<T> {
		
		private final ConstructorInfo<T> constructor;
		
		public BeanConstructor(CsvClassInfo<T> classInfo) {
			constructor = classInfo
					.defaultConstructor()
					.orElseThrow(() -> new CsvException("no suitable accessible constructor found"));
		}

		@Override
		public T get() {
			return constructor.construct();
		}
		
	}
	
	/**
	 * update field using setters (preferred) or field assignment
	 * 
	 * @author Arindam Biswas
	 */
	static class BeanFieldUpdater<T> implements BiConsumer<T, String[]> {
		
		private final List<AutoSetter> setters;
		
		public BeanFieldUpdater(CsvClassInfo<T> classInfo) {
			setters = findSetters(classInfo).toList();
		}

		private Stream<AutoSetter> findSetters(CsvClassInfo<T> classInfo) {
			return Stream.concat(findFieldSetters(classInfo), findMethodSetters(classInfo));
		}
		private Stream<AutoSetter> findFieldSetters(CsvClassInfo<T> classInfo) {
			return classInfo.csvFields().map(FieldInfo::getSetter);
		}
		private Stream<AutoSetter> findMethodSetters(CsvClassInfo<T> classInfo) {
			return classInfo.csvSetters().map(SetterInfo::new);
		}

		@Override
		public void accept(T instance, String[] columns) {
			setters.forEach(setter -> setter.autoSet(instance, columns));
		}
		
	}
	
}
