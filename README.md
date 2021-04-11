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

`FileReader`      - define how a file should be read | should include `skip` feature there - approach to be decided
`LineSplitter`    - define how a line should be split to extract columns
`OrmMapper`       - define how split line should be mapped to java object
`CsvReader`       - is an iterator that iterates through the lines in a file as per `FileReader` definition
                    and returns a java object as per `LineSplitter` and `OrmMapper` definition

### METHODS
`FileReader`      - lines() | head(n) | head(-n) | tail(n) | tail(-n)
`LineSplitter`    - delimiter(";") | fixed(int...) | line()
`OrmMapper`       - string() | string(n) | integer() | bigDecimal() | orm(User.class)
