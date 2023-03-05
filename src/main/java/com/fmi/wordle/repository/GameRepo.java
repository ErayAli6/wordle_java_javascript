package com.fmi.wordle.repository;

import com.fmi.wordle.model.Game;

public interface GameRepo {

    Game get(String id);

    void save(Game game);

    void update(Game game);

    Game delete(String id);
}
