package com.andrenunes.document_reader.exceptions;

public class InvalidExtensionException extends RuntimeException{

    public InvalidExtensionException(String ext) {
        super(String.format("Invalid file type: " + ext + "\n" + "Only use files with .xlsx extension!"));
    }

}
