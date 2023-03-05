package com.fmi.wordle.service;

import com.fmi.wordle.model.Game;
import com.fmi.wordle.model.Guess;
import com.fmi.wordle.repository.GameRepo;
import com.fmi.wordle.repository.WordRepo;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
public class GameServiceImpl implements GameService {

    final GameRepo gameRepo;

    final WordRepo wordRepo;

    @PostConstruct
    public void init() {
        wordRepo.list().stream().forEach(System.out::println);
    }

    @Override
    public Game startNewGame() {
        Game game = new Game();
        game.setId(UUID.randomUUID().toString());
        game.setStartedOn(LocalDateTime.now());
        game.setWord(wordRepo.getRandom());
        game.setGuesses(new ArrayList<>(game.getMaxGuesses()));
        return game;
    }

    @Override
    public Game makeGuess(@NonNull String gameId, @NonNull String word) throws UnknownWordException, GameNotFoundException {
        isGivenWordValid(wordRepo.list(), word);
        Game game = gameRepo.get(gameId);
        if (game == null) {
            throw new GameNotFoundException("Game with ID " + gameId + " not found.");
        }

        // Create a new guess with the given word and the current time
        Guess guess = new Guess();
        guess.setWord(word);
        guess.setMadeOn(LocalDateTime.now());

        // Check which letters of the guess word match the game word
        String gameWord = game.getWord();
        String matchString = getMatchString(word, gameWord);
        guess.setMatches(matchString);

        // Add the guess to the game and update it in the repository
        game.getGuesses().add(guess);
        gameRepo.update(game);
        return game;
    }

    @Override
    public Game getGame(String id) {
        var game = gameRepo.get(id);
        if (game == null) throw new GameNotFoundException(id);
        return game;
    }

    private String getMatchString(String guessWord, String gameWord) {
        StringBuilder matchString = new StringBuilder();
        for (int i = 0; i < guessWord.length(); i++) {
            char guessChar = guessWord.charAt(i);
            char gameChar = gameWord.charAt(i);
            if (guessChar == gameChar) {
                matchString.append(Guess.PLACE_MATCH);
            } else if (gameWord.indexOf(guessChar) >= 0) {
                matchString.append(Guess.LETTER_MATCH);
            } else {
                matchString.append(Guess.NO_MATCH);
            }
        }
        return matchString.toString();
    }

    private void isGivenWordValid(Collection<String> validWords, String word) {
        if (!validWords.contains(word)) {
            throw new UnknownWordException("Word " + word + " is not a valid 5-letter word.");
        }
    }
}
