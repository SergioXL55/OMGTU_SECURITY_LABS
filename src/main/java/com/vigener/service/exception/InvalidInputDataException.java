package com.vigener.service.exception;

public class InvalidInputDataException extends Exception {

    public InvalidInputDataException() {
        super("Input data is invalid or is Empty!");
    }
}
