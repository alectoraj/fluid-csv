module com.fluiapi.csv {
	
	// EXPORTS //
	
	exports com.fluidapi.csv.reader;
	exports com.fluidapi.csv.reader.deserializer;

	exports com.fluidapi.csv.writer;
	exports com.fluidapi.csv.writer.serializer;
	
	exports com.fluidapi.csv.annotations;
	exports com.fluidapi.csv.exception;
	exports com.fluidapi.csv.bean;
	
	// IMPORTS //
	
	requires lombok;
	requires org.apache.commons.lang3;
	requires org.hibernate.validator;
	requires jakarta.validation;
	requires jakarta.el;
	requires java.sql;
	
}