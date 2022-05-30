package io.github.iamaldren.services;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
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

    public static InputStream convertStringToStream(String string) throws Exception {
        return new ByteArrayInputStream(string.getBytes("UTF-8"));
    }

    public static void convertStringToFile(String path, String content) throws Exception {
        Files.write( Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
    }

    public static File getFile(String path) {
        return new File(path);
    }

}
