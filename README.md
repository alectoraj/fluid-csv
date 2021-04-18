# fluid-csv

A Fluid CSV Library that works with the standard Java 8+ Streams.

```
God mostPowerfulGod = Files
             .lines(Path.of("/path/to/gods.csv"))
             .map( fixed(24, 18) )
             .map( strip() )
             .map( auto(God.class) )
             .max( Comparator.comparingInt(God::getPowerLevel) )
             .orElse( NO_GOD );

Files.write( Path.of("/path/to/gods.csv"),
             listOfGods.stream()
             .map( auto(God.class) )
             .map( escapeStandard('"') )
             .map( enclose('"') )
             .map( delimiter(";") ));
```


## How does it work?

### READER
- You convert your regular CSV file using `BufferedReader` or `Files.lines(Path)` method
- Split the lines using delimiter, fixed lengths or columns from within quotation or brackets or any pair of character
- Optionally do some decorations e.g. removing spaces from around texts (specially useful for fixed length CSV files) or changing to upper/lower/title case texts
- Map that `String[]` to a java object by either picking up a column with custom `function`
  - or automatically convert to a java bean (aka pojo) configured with provided annotations
  - while auto-deserializing to beans, you may choose to use the aforementioned decorations specific to particular column(s)
- You then continue to work with your stream as usual

### WRITER
- You get a stream of your java bean
- Auto convert it to a `String[]` representing columns
- Do some decorative works like
  - escape certain characters
  - enclose columns with quotes or brackets or any pair of character
- Join the columns using delimiter, fixed lengths or pick only a single column
- Write or continue to work with your stream as intended

### Special benefits

- You're free to use only parts of it
- You can very well just use the splitting part, or decorative part, or auto-serialize/deserialize (aka auto-mapping) parts
- Auto-mapping feature can be utilized as a near-available orm-tool
  - all you need to do is pass your values as a sequential array of strings and configure indexes on your pojo
- Use them in any combination & order as you see fit & useful
- Although designed for streams, you may very well use them in a combination to produce `Function<String, YourPojo>`

## EXAMPLES

Let's first have a look at the structure by data types

### READER
```
Stream<String> // e.g. Files.lines(Path)
.map( CsvLineSplitter ) // returns Stream<String[]>
.map( CsvColumnDecorator ) // returns Stream<String[]> | is a mutable operation, i.e. may mutate the input String[]
.map( CsvBeanDeserializer ) // returns Stream<T> | T maybe any type, including your bean
... // continue your work with it
```
or
```
YourPojo dataAsPojo = CsvLineSplitter.andThen(CsvColumnDecorator).andThen(CsvBeanDeserializer).apply(dataAsString);
```

### WRITER
```
Stream<T>
.map( CsvBeanSerializer ) // returns Stream<String[]>
.map( CsvColumnDecorator ) // returns Stream<String[]> | is a mutable operation, i.e. may mutate the input String[]
.map( CsvColumnJoiner ) // returns Stream<String>
... // continue your work or write to file
```

Now let's have a look at some methods that provide the implementation
> you'll know about the data types when you code, so we'll write indicative names or values as parameters

### READER
Every method mentioned here are in `CsvReader` class

**`CsvLineSplitter`**
```
- delimiter(regex)
- fixed(24, 12, 10, 18, 50, 20, 1, 8, 8)
- dequote(quoteMark)
- dequote(bracketStart, bracketEnd)
- dequote(bracketStart, bracketEnd, usedEscapeCharacter)
- line() // just reads the whole line as a single column Csv value
```

**`CsvColumnDecorator`**
```
- strip()
- trim() // fun fact, the names suggests the use of respective method from String class
- unescape( backslashOrAlternate ) // in case your file has "Hormon D\'Souza" and you want it to be "Hormon D'Souza"
```

**`CsvBeanDeserializer`**
```
- pick( thenMap ) // picks first column
- pick( fromIndex, thenMap )
- string()
- string( fromIndex )
- integer()
- auto( YourPojo.class ) // make sure YourPojo is decorated with @CsvColumn and other annotations
```

### WRITER
Every method mentioned here are in `CsvWriter` class

**`CsvBeanSerializer`**
```
- auto( YourPojo.class )
- we only have one over here since converting to standard datatypes are fairly easy
```

**`CsvColumnDecorator`**
```
- enclose(quoteMark)
- enclose(bracketStart, bracketEnd)
- escape( backslashOrAlt, allCharactersThatNeedsEscaping )
- escapeStandard( allCharactersThatNeedsEscaping ) // uses standard escapeIndicator as backslash
```

**`CsvColumnJoiner`**
```
- delimiter(";")
- fixed(20, 12, 9, 10, 10, 8)
- only( pickOnlyASingleColumn )
```

### LIMITATIONS
- Support for nested classes are not here yet
- The pojo mapping is done strictly through index. Mapping through name over CSV files with first line or n-th line as header is out of our radar at the moment


## DEPENDENCY NOTE 
- Compiled using Java 16
- Jakarta Validation API is supported, instead of `javax.validation`
