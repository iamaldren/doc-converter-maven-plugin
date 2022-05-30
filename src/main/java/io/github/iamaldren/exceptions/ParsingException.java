package io.github.iamaldren.exceptions;

public class ParsingException extends Exception {

    private String message;

    public ParsingException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
