package com.andrenunes.document_reader.api;

import com.andrenunes.document_reader.exceptions.InvalidExtensionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(InvalidExtensionException.class)
    public ResponseEntity<Object> DocumentInvalid(InvalidExtensionException exception) {
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Object> DocumentNotFound(FileNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Object> IoGenericException(IOException ex) {
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
