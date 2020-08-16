/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.service;

import com.aspire.crackthecodeapi.TestApplicationConfiguration;
import com.aspire.crackthecodeapi.data.GameDao;
import com.aspire.crackthecodeapi.data.RoundDao;
import com.aspire.crackthecodeapi.models.Game;
import com.aspire.crackthecodeapi.models.Round;
import com.aspire.crackthecodeapi.service.util.Util;
import java.util.List;
import static org.junit.Assert.*;
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
public class ServiceLayerImpTest {

    @Autowired
    private ServiceLayer service;

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    private Game game;
    private Game game2;

    @Before
    public void setUp() {

        //delete all rounds
        List<Round> rounds = roundDao.getAllRounds();
        rounds.forEach(round -> {
            roundDao.deleteRoundByGameId(round.getGameId());
        });

        List<Game> games = gameDao.getAllGames();
        for (Game game : games) {
            gameDao.deleteGameByGameId(game.getGameId());
        }

        //Game with bad guess
        game = new Game();
        game.setGameId(2);
        game.setAnswer("1234");
        game.setStatus(Util.getGAME_STATUS_IN_PROGRESS());
        game.setGuess("1235");

        //game with correct guess
        game2 = new Game();
        game2.setGameId(2);
        game2.setAnswer("5678");
        game2.setStatus(Util.getGAME_STATUS_IN_PROGRESS());
        game2.setGuess("5678");
    }
//
//    @Test
//    public void testGenerateUniqueAnswer() throws Exception {
//
//        Set testSet = new HashSet();
//
//        String uniqueAnswer = service.generateAnswer();
//
//        uniqueAnswer.chars()
//                .mapToObj(c -> Character.valueOf((char) c))
//                .forEach(c -> testSet.add(c));
//
//        boolean sameSize = uniqueAnswer.length() == testSet.size();
//
//        assertTrue(sameSize);
//
//    }
//
//    @Test
//    public void testGenerateUniqueAnswerLength() throws Exception {
//
//        String uniqueAnswer = service.generateAnswer();
//
//        boolean lengthIsFour = uniqueAnswer.length() == 4;
//
//        assertTrue(lengthIsFour);
//
//    }

//    @Test
//    public void testFindGameByGameId() throws Exception {
//        System.out.println("11");
//        Game gameClone = new Game();
//        gameClone.setGameId(1);
//        gameClone.setAnswer("1234");
//        gameClone.setStatus(Util.getGAME_STATUS_IN_PROGRESS());
//        gameClone.setAnswer("1235");
//
//        System.out.println("22");
//
//        Game foundGame = service.findGamebyId(gameClone.getGameId());
//
//        System.out.println("33");
//
//        assertEquals(gameClone, foundGame);
//
//        System.out.println("44");
//    }
    @Test
    public void testGetAllGames() throws Exception {

        game = service.createGame(game);

        game2 = service.createGame(game2);

        List<Game> allGames = service.getAllGames();

        System.out.println("allGames " + allGames.size());

        allGames.forEach(g -> {
            System.out.println("g " + g.toString());
        });

        assertEquals(2, allGames.size());
//        assertTrue(allGames.contains(game));
//        assertTrue(allGames.contains(game2));
//
    }

}
