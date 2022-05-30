package io.github.iamaldren.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.json.JSONObject;
import org.json.XML;

public final class XMLConverterService extends AbstractFileService {

    private XMLConverterService() {}

    public static String convertXmlToYaml(String xml) throws Exception {

        JSONObject xmlJSONObj = XML.toJSONObject(xml);
        String json = xmlJSONObj.toString();

        JsonNode jsonNode = new ObjectMapper().readTree(json);
        return new YAMLMapper().writeValueAsString(jsonNode);
    }

}
