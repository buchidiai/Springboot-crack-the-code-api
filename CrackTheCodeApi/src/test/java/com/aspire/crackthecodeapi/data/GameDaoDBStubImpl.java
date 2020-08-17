/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.data;

import com.aspire.crackthecodeapi.models.Game;
import com.aspire.crackthecodeapi.service.util.Util;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 *
 * @author louie
 */
@Repository
@Profile("testing")
public class GameDaoDBStubImpl implements GameDao {

    private Game onlyGame;

    @Autowired
    public GameDaoDBStubImpl() {

        this.onlyGame = new Game();
        this.onlyGame.setGameId(1);
        this.onlyGame.setAnswer("1234");
        this.onlyGame.setStatus(Util.getGAME_STATUS_IN_PROGRESS());
        this.onlyGame.setGuess("1235");

    }

    @Override
    public Game createGame(Game game) {
        if (game.getGameId() == onlyGame.getGameId()) {
            return onlyGame;
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteGameByGameId(int id) {

        if (id == onlyGame.getGameId()) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Game findGameByGameId(int id) {
        if (id == onlyGame.getGameId()) {

            return onlyGame;
        } else {
            return null;
        }
    }

    @Override
    public boolean updateGame(Game game) {
        if (game.getGameId() == onlyGame.getGameId()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> allGames = new ArrayList<>();

        allGames.add(onlyGame);
        return allGames;
    }

}
