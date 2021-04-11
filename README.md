# fluid-csv
> Under Construction

## Fluid Csv Reader and Writer

### Intended design | for Reader:
```
FileReader()
.using( LineSplitter )
.as( OrmMapper )
.from( File | Path )
```

### Involved Classes

`FileReader`
> define how a file should be read\
> should include `skip` feature there - *approach to be decided*\
> `lines() | head(n) | head(-n) | tail(n) | tail(-n)`

`LineSplitter`
> define how a line should be split to extract columns\
> `delimiter(";") | fixed(int...) | line()`

`OrmMapper`
> define how split line should be mapped to java object\
> `string() | string(n) | integer() | bigDecimal() | orm(User.class)`

`CsvReader`
> is an iterator that iterates through the lines in a file as per `FileReader` definition
> returns a java object as per `LineSplitter` and `OrmMapper` definition
