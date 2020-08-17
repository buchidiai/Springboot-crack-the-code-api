/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.service;

import com.aspire.crackthecodeapi.TestApplicationConfiguration;
import com.aspire.crackthecodeapi.controller.GameResponse;
import com.aspire.crackthecodeapi.data.GameDao;
import com.aspire.crackthecodeapi.data.RoundDao;
import com.aspire.crackthecodeapi.models.Game;
import com.aspire.crackthecodeapi.service.util.Util;
import java.util.HashSet;
import java.util.Set;
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
public class ServiceLayerImpTest {

    @Autowired
    private ServiceLayer service;

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    private Game game = null;

    @Before
    public void setUp() {

        game = new Game();
        game.setGameId(1);
        game.setAnswer("1234");
        game.setStatus(Util.getGAME_STATUS_IN_PROGRESS());
        game.setGuess("1235");

    }

    @Test
    public void testCalculateResult() throws Exception {

        GameResponse gameResponse = service.calculatedResult(game);

        System.out.println("gameResponse " + gameResponse.toString());

        assertTrue(gameResponse.getResult().equals("e:3:p:0"));
    }

    @Test
    public void testGenerateUniqueAnswer() throws Exception {

        Set testSet = new HashSet();

        String uniqueAnswer = service.generateAnswer();

        uniqueAnswer.chars()
                .mapToObj(c -> Character.valueOf((char) c))
                .forEach(c -> testSet.add(c));

        boolean sameSize = uniqueAnswer.length() == testSet.size();

        assertTrue(sameSize);

    }

    @Test
    public void testGenerateUniqueAnswerLength() throws Exception {

        String uniqueAnswer = service.generateAnswer();

        boolean lengthIsFour = uniqueAnswer.length() == 4;

        assertTrue(lengthIsFour);

    }

    @Test
    public void testFindGameByGameId() throws Exception {

        Game foundGame = service.findGameByGameId(game.getGameId());

        assertEquals(game.getGameId(), foundGame.getGameId());

    }

    @Test
    public void testFindGameByGameIdHidesAnswer() throws Exception {

        Game foundGame = service.findGameByGameId(game.getGameId());

        assertTrue(foundGame.getAnswer().equals("N/A"));

    }

    @Test
    public void testGetAllGames() throws Exception {

        game = service.createGame(game);

        assertEquals(1, service.getAllGames().size());

        assertTrue(service.getAllGames().get(0).equals(game));
    }

    @Test
    public void testGetAllRoundsByGame() throws Exception {

        game = service.createGame(game);

        assertEquals(1, service.getAllRoundsByGameId(game.getGameId()).size());

        assertTrue(service.getAllGames().get(0).equals(game));
    }

    @Test
    public void testCreateGame() throws Exception {

        game = service.createGame(game);

        Game foundGame = service.findGameByGameId(game.getGameId());

        assertEquals(game.getGameId(), foundGame.getGameId());

    }
}
