package com.fmi.wordle.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Guess {

    public static final char PLACE_MATCH = 'P';

    public static final char LETTER_MATCH = 'L';

    public static final char NO_MATCH = 'N';

    String word;

    LocalDateTime madeOn;

    String matches;
}
