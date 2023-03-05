package com.fmi.wordle.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Game {

    String id;

    String word;

    LocalDateTime startedOn;

    int maxGuesses = 6;

    List<Guess> guesses;
}
