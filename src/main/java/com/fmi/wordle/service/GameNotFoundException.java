package com.fmi.wordle.service;

import static java.lang.String.format;

public class GameNotFoundException extends RuntimeException{

    public GameNotFoundException(String gameId) {
        super(format("Game with ID [%s] does not exist", gameId));
    }
}
