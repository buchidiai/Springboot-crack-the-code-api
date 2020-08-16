/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.data;

import com.aspire.crackthecodeapi.TestApplicationConfiguration;
import com.aspire.crackthecodeapi.models.Game;
import com.aspire.crackthecodeapi.models.Round;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.Assert.assertEquals;
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
public class RoundDaoDBTest {

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    private static final String GAME_IN_PROGRESS = "in-Progress";
    private static final String FINISHED_GAME = "finished";

    private Game game = null;
    private Round round = null;
    private Round round2 = null;
    private static final LocalDateTime now = LocalDateTime.now();

    @Before
    public void setUp() {

        //delete all rounds
        List<Round> rounds = roundDao.getAllRounds();
        rounds.forEach(round -> {
            roundDao.deleteRoundByGameId(round.getGameId());
        });
        //delete all games
        List<Game> games = gameDao.getAllGames();
        games.forEach(game -> {
            gameDao.deleteGameByGameId(game.getGameId());
        });

        //create new game object
        game = new Game();
        //set answer
        game.setAnswer("1234");
        //set status
        game.setStatus(GAME_IN_PROGRESS);

        //add game to db
        game = gameDao.createGame(game);

        //play round 1
        //create round object
        round = new Round();

        round.setGameId(game.getGameId());
        round.setGuess("1236");
        round.setTime(now);
        round.setExact(3);
        round.setPartial(0);
        round.setResult("e:" + round.getExact() + ":p:" + round.getPartial());
        round.setRoundNumber(1);
        round.setStatus(GAME_IN_PROGRESS);

        //play round 2
        //create round object
        round2 = new Round();

        round2.setGameId(game.getGameId());
        round2.setGuess("1234");
        round2.setTime(now);
        round2.setExact(4);
        round2.setPartial(0);
        round2.setResult("e:" + round.getExact() + ":p:" + round.getPartial());
        round2.setRoundNumber(2);
        round2.setStatus(FINISHED_GAME);

    }

    @Test
    public void testCreateRound() {

        int roundNumber = 0;

        roundDao.createRound(roundNumber, game.getGameId());

        Round round = roundDao.getRoundByGameId(game.getGameId());

        assertEquals(round.getGameId(), game.getGameId());
    }

    @Test
    public void testGetRoundNumber() {

        int roundNumber = 0;

        roundDao.createRound(roundNumber, game.getGameId());

        Round round = roundDao.getRoundByGameId(game.getGameId());

        int roundNum = roundDao.getRoundNumberByGameId(game.getGameId());

        assertEquals(round.getRoundNumber(), roundNum);
    }

    @Test
    public void testGetAllRoundsByGameId() {

        roundDao.addRound(round, round.getRoundNumber(), game.getGameId());

        roundDao.addRound(round2, round2.getRoundNumber(), game.getGameId());

        List<Round> rounds = roundDao.getAllRoundsByGameId(game.getGameId());

        assertEquals(2, rounds.size());
        assertTrue(rounds.get(0).getGameId() == game.getGameId());
        assertTrue(rounds.get(1).getGameId() == game.getGameId());
    }

    @Test
    public void testAddRound() {

        roundDao.addRound(round, round.getRoundNumber(), game.getGameId());

        List<Round> rounds = roundDao.getAllRounds();

        assertEquals(1, rounds.size());
        assertTrue(rounds.get(0).getGameId() == game.getGameId());

    }

    @Test
    public void testGetRound() {

        roundDao.addRound(round, round.getRoundNumber(), game.getGameId());

        Round foundRound = roundDao.getRoundByGameId(game.getGameId());

        assertEquals(foundRound.getGameId(), game.getGameId());

    }

    @Test
    public void testDeleteRound() {

        roundDao.addRound(round, round.getRoundNumber(), game.getGameId());

        roundDao.deleteRoundByGameId(game.getGameId());

        List<Round> rounds = roundDao.getAllRounds();

        assertEquals(0, rounds.size());

    }

    @Test
    public void testGetAllRounds() {

        roundDao.addRound(round, round.getRoundNumber(), game.getGameId());

        roundDao.addRound(round2, round2.getRoundNumber(), game.getGameId());

        List<Round> rounds = roundDao.getAllRounds();

        assertEquals(2, rounds.size());
        assertTrue(rounds.get(0).getGameId() == game.getGameId());
        assertTrue(rounds.get(1).getGameId() == game.getGameId());
    }
}
