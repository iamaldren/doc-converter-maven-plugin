# doc-converter-maven-plugin

## Supported Input Types
- CSV

## Supported Output Types
- JSON
- YAML

## TODO Supported Input Types
- XML
- JSON
- YAML

## TODO Supported Output Types
- XML
- CSV

## Configurations

```xml
<configuration>
    <inputDir>${project.basedir}/src/main/resources/data/bank.csv</inputDir>
    <outputDir>${project.basedir}/src/main/resources/data/bank.yaml</outputDir>
    <inputType>csv</inputType>
    <outputType>yaml</outputType>
    <mapHeader>
        <csvColumn1>fullName</csvColumn1>
        <csvColumn2>age</csvColumn2>
    </mapHeader>
</configuration>
```
### Where:
- `inputDir` = directory of source file
- `inputType` = file type of source
- `outputDir` = directory where output file will be generated
- `outputType` = target output file extension

The four configurations above are required, and if one is missing the program will exit.

The configuration `mapHeader` is optional. This config is primarily for mapping the header to custom names. Do note that for headers with white space in between, it must be removed.

E.g.
```csv
Full Name,Age
Aldren Bobiles,20
```
The `mapHeader` configuration for the above sample should be:
```xml
<mapHeader>
    <FullName>fullName</FullName>
    <Age>age</Age>
</mapHeader>
```