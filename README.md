# fluid-csv
> Under Construction

## Fluid Csv Reader and Writer

Intended design | for Reader:
```
Csv
.using( delimiter(";") | fixed(int...) | line() ) : CsvLineReader
.as( string() | string(n) | integer() | bigDecimal() | orm(User.class) ) : CsvMapper
.read( lines() | head(n) | head(-n) | tail(n) | tail(-n) | skip(n).head(n) ) : CsvReader
.of( file | path | reader ) : Sequence
.stream() : Stream
...

Csv {
  static using( CsvLineInterpreter );
}

CsvLineInterpreter {
  String[] interpret( String line );
}

CsvLineReader {
  CsvMapper as( CsvRowMapper );
}

CsvRowMapper {
  static CsvRowMapper string() -> string(0);
  static CsvRowMapper string(int index);
  
  static CsvRowMapper integer() -> integer(0);
  static CsvRowMapper integer(int index);
  
  static CsvRowMapper bigDecimal() -> bigDecimal(0);
  static CsvRowMapper bigDecimal(int index);
  
  static CsvRowMapper orm(Class);
  
  T map(String[] interpretedLine);
  CsvRowMapper then(Function mapper);
}

CsvMapper {
  CsvReader read( CsvContentReader );
}

CsvReader {
  Sequence of( String file );
  Sequence of( File file );
  Sequence of( Path file );
  Sequence of( Reader reader );
}
```
