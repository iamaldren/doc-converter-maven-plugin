package io.github.iamaldren.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import io.github.iamaldren.exceptions.ParsingException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class CsvConverterService extends AbstractFileService {

    private static final Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private CsvConverterService() {
        log.setLevel(Level.INFO);
    }

    public static String convertCsvToJson(InputStream in) throws ParsingException {
        List<Map<String, String>> response = new LinkedList<>();

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();

        try {
            MappingIterator<Map<String, String>> iterator = mapper.reader(Map.class)
                    .with(schema)
                    .readValues(in);

            while (iterator.hasNext()) {
                response.add(iterator.next());
            }

            log.info("Size is " + response.size());

            JSONArray json = new JSONArray(response);
            return json.toString();
        } catch (IOException e) {
            log.log(Level.SEVERE, "Error parsing csv to json", e);
            throw new ParsingException(e.getLocalizedMessage());
        }
    }

    public static String convertCsvToYaml(InputStream in) throws ParsingException, JsonProcessingException {
        String json = convertCsvToJson(in);

        JsonNode jsonNode = new ObjectMapper().readTree(json);
        return new YAMLMapper().writeValueAsString(jsonNode);
    }

}
