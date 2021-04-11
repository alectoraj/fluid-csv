# fluid-csv
> Under Construction

## Fluid Csv Reader and Writer

### Intended design | for Reader:
```
Stream<String>
.map( LineSplitter )
.map( OrmMapper )
...
```
or
```
Stream<Sting>
.map( LineSplitter.andThen(OrmMapper) )
...
```
> Stream<String> maybe obtained in usual way like `Files.lines(Path)`

### Involved Classes

`LineSplitter`
> define how a line should be split to extract columns\
> `delimiter(";") | fixed(int...) | line()`

`OrmMapper`
> define how split line should be mapped to java object\
> `string() | string(n) | integer() | bigDecimal() | orm(User.class)`
