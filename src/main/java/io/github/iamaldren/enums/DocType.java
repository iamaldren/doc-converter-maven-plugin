package io.github.iamaldren.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum DocType {

    XML("xml"),
    CSV("csv"),
    JSON("json"),
    YAML("yaml");

    private final String name;

    private static final Map<String, DocType> BY_NAME = new HashMap<>();

    static {
        Arrays.stream(values())
                .forEach(type -> {
                    BY_NAME.put(type.name, type);
                });
    }

    DocType(String name) {
        this.name = name;
    }

    public static DocType getName(String value) {
        return BY_NAME.get(value);
    }

}
