package com.fmi.wordle.service;

import com.fmi.wordle.model.Game;
import com.fmi.wordle.repository.GameRepo;
import com.fmi.wordle.repository.WordRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class GameServiceImplTest {

    @Mock
    private GameRepo gameRepo;

    @Mock
    private WordRepo wordRepo;

    @InjectMocks
    private GameServiceImpl gameService;

    @Test
    public void testMakeGuess() {
        //Given
        String gameId = "12345";
        String gameWord = "мисля";
        String guessWord = "мамин";
        Game game = createGame(gameId, gameWord);
        when(wordRepo.list()).thenReturn(Arrays.asList("мисля", "тегля", "лягам", "мамин", "сусам"));
        when(gameRepo.get(gameId)).thenReturn(game);

        //When
        Game result = gameService.makeGuess(gameId, guessWord);

        //Then
        verify(wordRepo).list();
        verify(gameRepo).get(gameId);
        verify(gameRepo).update(game);
        assertThat(result.getGuesses().get(0).getMatches(), is("PNLLN"));
    }

    private Game createGame(String gameId, String gameWord) {
        Game game = new Game();
        game.setId(gameId);
        game.setWord(gameWord);
        game.setGuesses(new ArrayList<>());
        return game;
    }
}