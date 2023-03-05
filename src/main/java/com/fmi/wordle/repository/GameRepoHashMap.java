package com.fmi.wordle.repository;

import com.fmi.wordle.model.Game;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class GameRepoHashMap implements GameRepo {

    ConcurrentHashMap<String, Game> games = new ConcurrentHashMap<>();

    @Override
    public Game get(String id) {
        return games.get(id);
    }

    @Override
    public void save(Game game) {
        games.put(game.getId(), game);
    }

    @Override
    public void update(Game game) {
        save(game);
    }

    @Override
    public Game delete(String id) {
        return games.remove(id);
    }
}
