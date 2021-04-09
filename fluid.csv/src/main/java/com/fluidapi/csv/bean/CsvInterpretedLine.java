package com.fluidapi.csv.bean;

public record CsvInterpretedLine(String[] columns) {
	
	private static final CsvInterpretedLine EMPTY_LINE = new CsvInterpretedLine(new String[0]);

	public String nullIfAbsent(int index) {
		return altIfAbsent(index, null);
	}
	public String altIfAbsent(int index, String alt) {
		return isIndexValid(index) ? columns[index] : alt;
	}
	
	private boolean isIndexValid(int index) {
		return 0 <= index && index < columns.length;
	}
	
	public static CsvInterpretedLine empty() {
		return EMPTY_LINE;
	}
	public static CsvInterpretedLine of(String...strings) {
		return new CsvInterpretedLine(strings);
	}
}
