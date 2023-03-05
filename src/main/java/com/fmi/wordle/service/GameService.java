package com.fmi.wordle.service;

import com.fmi.wordle.model.Game;

public interface GameService {

    Game startNewGame();

    Game makeGuess(String id, String word);

    Game getGame(String id);
}
