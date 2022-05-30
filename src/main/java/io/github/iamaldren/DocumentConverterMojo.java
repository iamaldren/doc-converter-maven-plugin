package io.github.iamaldren;

import io.github.iamaldren.enums.DocType;
import io.github.iamaldren.services.CsvConverterService;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.util.logging.Level;
import java.util.logging.Logger;

@Mojo(name = "doc-converter", defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public class DocumentConverterMojo extends AbstractMojo {

    private static final Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Parameter(property = "inputDir")
    private String inputDir;

    @Parameter(property = "outputDir")
    private String outputDir;

    @Parameter(property = "inputType")
    private String inputType;

    @Parameter(property = "outputType")
    private String outputType;

    @Parameter(property = "prefix")
    private String prefix;

    @Parameter(property = "mapHeader")
    private String mapHeader;

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject mavenProject;

    private static final String TRUE = "true";
    private static final String FALSE = "false";

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        log.setLevel(Level.INFO);

        if(!validateParams()) {
            return;
        }

        DocType input = DocType.getName(inputType.toLowerCase());

        log.info(String.format("Input Dir: %s", inputDir));
        log.info(String.format("Output Dir: %s", outputDir));

        switch(input) {
            case XML:
                convertXmlDoc();
                break;
            case CSV:
                convertCsvDoc();
                break;
            case JSON:
                convertJsonDoc();
                break;
            case YAML:
                convertYamlDoc();
                break;
            default:
                log.warning("Input type not yet supported.");
        }
    }

    private boolean validateParams() {
        boolean isValid = true;

        if(inputDir == null || inputDir.isBlank()) {
            log.warning("Input Directory is missing, exiting the program");
            isValid = false;
        }

        if(outputDir == null || outputDir.isBlank()) {
            log.warning("Output Directory is missing, exiting the program");
            isValid = false;
        }

        if(inputType == null || inputType.isBlank()) {
            log.warning("Input type is missing, exiting the program");
            isValid = false;
        }

        if(outputType == null || outputType.isBlank()) {
            log.warning("Output type is missing, exiting the program");
            isValid = false;
        }

        if((mapHeader != null && !mapHeader.isBlank()) &&
                (!mapHeader.equalsIgnoreCase(TRUE)) && !mapHeader.equalsIgnoreCase(FALSE)) {
            log.warning("mapHeader property only expects true or false value");
            isValid = false;
        }

        return isValid;
    }

    private void convertXmlDoc() {
        DocType output = DocType.getName(outputType.toLowerCase());

        switch(output) {
            case CSV:
                convertXmlToCsv();
                break;
            case JSON:
                convertXmlToJson();
                break;
            case YAML:
                convertXmlToYaml();
                break;
            default:
                log.warning("Output type not yet supported.");
        }
    }

    private void convertXmlToCsv() {

    }

    private void convertXmlToJson() {

    }

    private void convertXmlToYaml() {

    }

    private void convertCsvDoc() {
        DocType output = DocType.getName(outputType.toLowerCase());

        switch(output) {
            case XML:
                convertCsvToXml();
                break;
            case JSON:
                convertCsvToJson();
                break;
            case YAML:
                convertCsvToYaml();
                break;
            default:
                log.warning("Output type not yet supported.");
        }
    }

    private void convertCsvToXml() {

    }

    private void convertCsvToJson() {

    }

    private void convertCsvToYaml() {
        try {
            String csv = CsvConverterService.convertFileToString(inputDir);
            String yaml = CsvConverterService.convertCsvToYaml(csv, prefix, Boolean.valueOf(mapHeader));
            CsvConverterService.convertStringToFile(outputDir, yaml);
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    private void convertJsonDoc() {
        DocType output = DocType.getName(outputType.toLowerCase());

        switch(output) {
            case XML:
                convertJsonToXml();
                break;
            case CSV:
                convertJsonToCsv();
                break;
            case YAML:
                convertJsonToYaml();
                break;
            default:
                log.warning("Output type not yet supported.");
        }
    }

    private void convertJsonToXml() {

    }

    private void convertJsonToCsv() {

    }

    private void convertJsonToYaml() {

    }

    private void convertYamlDoc() {
        DocType output = DocType.getName(outputType.toLowerCase());

        switch(output) {
            case XML:
                convertYamlToXml();
                break;
            case CSV:
                convertYamlToCsv();
                break;
            case JSON:
                convertYamlToJson();
                break;
            default:
                log.warning("Output type not yet supported.");
        }
    }

    private void convertYamlToXml() {

    }

    private void convertYamlToCsv() {

    }

    private void convertYamlToJson() {

    }


}
