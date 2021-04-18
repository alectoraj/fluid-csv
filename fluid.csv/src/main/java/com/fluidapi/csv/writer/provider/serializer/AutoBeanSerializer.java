package com.fluidapi.csv.writer.provider.serializer;

import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.fluidapi.csv.exception.CsvFormatException;
import com.fluidapi.csv.provider.bean.AnnotatedInfo;
import com.fluidapi.csv.provider.bean.AutoGetter;
import com.fluidapi.csv.provider.bean.CsvClassInfo;
import com.fluidapi.csv.provider.bean.FieldInfo;
import com.fluidapi.csv.provider.bean.GetterInfo;
import com.fluidapi.csv.provider.bean.MethodInfo;
import com.fluidapi.csv.writer.CsvBeanSerializer;
import com.fluidapi.csv.writer.provider.serializer.column.BlankGetter;

public class AutoBeanSerializer<T> implements CsvBeanSerializer<T> {
	
	final List<AutoGetter> extractors;

	public AutoBeanSerializer(Class<T> type) {
		this.extractors = listExtractors(type);
	}

	@Override
	public String[] convert(T t) {
		return extractors.stream()
				.map(mapper -> mapper.apply(t))
				.toArray(String[]::new);
	}
	
	private List<AutoGetter> listExtractors(Class<T> type) {
		CsvClassInfo<T> classInfo = new CsvClassInfo<>(type);

		// get both get operations
		// by field (respective getter is used if present)
		// by method, not necessarily corresponds to a simple field
		Stream<GetOperation> fieldGetters = findFieldGetters(classInfo);
		Stream<GetOperation> methodGetters = findMethodGetters(classInfo);
		
		// find all getters and map by index
		// just sorting wouldn't help, since in the configuration, columns maybe missing
		Map<Integer, AutoGetter> getters = Stream
			.concat(fieldGetters, methodGetters)
			.collect(toMap(GetOperation::colIndex, GetOperation::getter,
					(u, v) -> { throw new CsvFormatException(
					"multiple columns marked with same column index, use @ReadOnly to exclude the non-primary ones"); }));
		
		// prepare to form list
		final AutoGetter blankGetter = new BlankGetter();
		final int maxColumnIndex = getters.keySet().stream()
				.mapToInt(Integer::intValue).max().orElse(0);
		
		// ensure consecutive indices
		// for configured, get configured mapper
		// for non-configured, get blank mapper
		return IntStream.rangeClosed(0, maxColumnIndex)
				.mapToObj(index -> getters.getOrDefault(index, blankGetter))
				.toList();
		
	}

	// 2 out of 3 lines from below methods may look like repetition
	// but they're referring to 2 totally different types and insider operations
	// that cannot be combined easily.
	// only thanks to deeper method segregation and lambda style, they look same
	
	private Stream<GetOperation> findFieldGetters(CsvClassInfo<T> classInfo) {
		return classInfo.csvFields()
				.filter(FieldInfo::isNotReadOnly)
				.map(GetOperation::new);
	}

	private Stream<GetOperation> findMethodGetters(CsvClassInfo<T> classInfo) {
		return classInfo.csvGetters()
				.filter(MethodInfo::isNotReadOnly)
				.map(GetOperation::new);
	}
	
	private static record GetOperation ( AutoGetter getter, AnnotatedInfo<?> origin ) {

		public GetOperation( FieldInfo field ) {
			this(field.getGetter(), field);
		}
		
		public GetOperation( MethodInfo method ) {
			this(new GetterInfo(method), method);
		}
		
		int colIndex() {
			return origin.getCsvColumnIndex();
		}
		
	}

}
