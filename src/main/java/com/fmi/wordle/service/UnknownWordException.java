package com.fmi.wordle.service;

import static java.lang.String.format;

public class UnknownWordException extends RuntimeException {

    public UnknownWordException(String word) {
        super(format("Unknown word [%s]", word));
    }
}
