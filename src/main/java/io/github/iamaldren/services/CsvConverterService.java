package io.github.iamaldren.services;

import com.thoughtworks.xstream.XStream;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class CsvConverterService extends AbstractFileService {

    private static final Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private CsvConverterService() {
        log.setLevel(Level.INFO);
    }

    public static String convertCsvToXml(String csv, String prefix, boolean isMapHeader) throws Exception {

        CsvParserSettings settings = new CsvParserSettings();
        settings.detectFormatAutomatically();
        settings.setEmptyValue("");
        settings.setNullValue("");
        CsvParser parser = new CsvParser(settings);

        InputStream input = convertStringToStream(csv);

        List<String[]> rows = parser.parseAll(input);

        XStream xstream = new XStream();
        if(prefix != null && !prefix.isBlank()) {
            if(prefix.contains(".")) {
                String[] prefixes = prefix.split(".");
                Arrays.stream(prefixes)
                        .forEach(pfx -> xstream.alias(pfx, Object.class));
            } else {
                xstream.alias(prefix, Object.class);
            }
        } else {
            xstream.alias("rows", List.class);
            xstream.alias("row", String[].class);
            xstream.alias("item", String.class);
        }

        if(isMapHeader) {
            log.info("Mapping headers");
            String[] headers = rows.get(0);
            Arrays.stream(headers)
                    .map(header -> header.trim().replaceAll("\\s+",""))
                    .peek(header -> log.info(String.format("Header: %s", header)))
                    .forEach(header -> xstream.alias(header, String.class));
        }

        rows.remove(0);
        return xstream.toXML(rows);
    }

    public static String convertCsvToJson(String csv) {

    }

    public static String convertCsvToYaml(String csv, String prefix, boolean isMapHeader) throws Exception{
        String xml = convertCsvToXml(csv, prefix, isMapHeader);
        return XMLConverterService.convertXmlToYaml(xml);
    }

}
