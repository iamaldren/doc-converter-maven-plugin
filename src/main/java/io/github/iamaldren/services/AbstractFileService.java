package io.github.iamaldren.services;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public abstract class AbstractFileService {

    public static String convertFileToString(String path) throws Exception {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        String fileAsString = new String(encoded, StandardCharsets.UTF_8);
        return fileAsString;
    }

    public static InputStream convertFileToStream(String path) throws FileNotFoundException {
        return new FileInputStream(path);
    }

    public static void convertStringToFile(String path, String content) throws Exception {
        Files.write( Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
    }

}
