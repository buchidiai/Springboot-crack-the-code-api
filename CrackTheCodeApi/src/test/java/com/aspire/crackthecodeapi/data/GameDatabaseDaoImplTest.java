/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.data;

import com.aspire.crackthecodeapi.TestApplicationConfiguration;
import com.aspire.crackthecodeapi.models.Game;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author louie
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GameDatabaseDaoImplTest {

    @Autowired
    GameDao gameDao;

    private static final String GAME_IN_PROGRESS = "in-Progress";
    private static final String FINISHED_GAME = "finished";

    @Before
    public void setUp() {

        List<Game> games = gameDao.getAllGames();
        for (Game game : games) {
            gameDao.deleteGame(game.getGameId());
        }

    }

    @Test
    public void testAddGame() {
        //create new game object
        Game game = new Game();
        //set answer
        game.setAnswer("1234");
        //set status
        game.setStatus(GAME_IN_PROGRESS);

        Game createdGame = gameDao.createGame(game);

        assertEquals(game, createdGame);
    }

    @Test
    public void testAddGameStatus() {
        //create new game object
        Game game = new Game();
        //set answer
        game.setAnswer("1234");
        //set status
        game.setStatus(GAME_IN_PROGRESS);

        Game createdGame = gameDao.createGame(game);

        assertEquals(game.getStatus(), createdGame.getStatus());
    }

    @Test
    public void testAddGameAnswer() {
        //create new game object
        Game game = new Game();
        //set answer
        game.setAnswer("1234");
        //set status
        game.setStatus(GAME_IN_PROGRESS);

        Game createdGame = gameDao.createGame(game);

        assertEquals(game.getAnswer(), createdGame.getAnswer());
    }

    @Test
    public void testGetAllGames() {
        //create new game object
        Game game = new Game();
        game.setAnswer("1234");
        game.setStatus(GAME_IN_PROGRESS);
        Game createdGame = gameDao.createGame(game);

        //create new game object
        Game game1 = new Game();
        game1.setAnswer("5678");
        game1.setStatus(GAME_IN_PROGRESS);
        Game createdGame1 = gameDao.createGame(game1);

        //get all games
        List<Game> games = gameDao.getAllGames();

        assertEquals(2, games.size());
        assertTrue(games.contains(createdGame));
        assertTrue(games.contains(createdGame1));
    }

    @Test
    public void testfindGameById() {
        //create new game object
        Game game = new Game();
        game.setAnswer("1234");
        game.setStatus(GAME_IN_PROGRESS);
        Game createdGame = gameDao.createGame(game);
        //get all games
        List<Game> games = gameDao.getAllGames();

        //get game added ~> its in 1st index
        Game onlyGame = games.get(0);

        //get only game by id
        Game retrievedGame = gameDao.findGamebyId(onlyGame.getGameId());

        assertEquals(1, games.size());

        assertTrue(onlyGame.getGameId() == createdGame.getGameId());

        assertTrue(retrievedGame.getGameId() == createdGame.getGameId());
    }

    @Test
    public void testupdateGame() {

        //create new game object
        Game game = new Game();
        game.setAnswer("1234");
        game.setStatus(GAME_IN_PROGRESS);
        //create game
        Game createdGame = gameDao.createGame(game);

        //update game field
        createdGame.setStatus(FINISHED_GAME);
        createdGame.setGuess("1234");

        //save to db
        gameDao.updateGame(createdGame);
        //get updated game from db
        Game updatedGame = gameDao.findGamebyId(createdGame.getGameId());

        assertEquals(createdGame.getStatus(), updatedGame.getStatus());
        assertNotNull(updatedGame.getAnswer());

    }

    @Test
    public void testDeleteGame() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setStatus(GAME_IN_PROGRESS);

        Game createdGame = gameDao.createGame(game);

        gameDao.deleteGame(createdGame.getGameId());

        List<Game> games = gameDao.getAllGames();

        assertEquals(0, games.size());
        assertTrue(!games.contains(createdGame));

    }
}
