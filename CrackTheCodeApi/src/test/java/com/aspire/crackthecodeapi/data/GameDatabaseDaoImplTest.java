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

        System.out.println("here");
        //create new game object
        Game game = new Game();
        //set answer
        game.setAnswer("1234");
        //set status
        game.setStatus(GAME_IN_PROGRESS);

        Game createdGame = gameDao.createGame(game);

        assertEquals(game, createdGame);
    }

}
