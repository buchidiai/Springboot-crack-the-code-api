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
import org.springframework.context.annotation.Profile;

/**
 *
 * @author louie
 */
@Profile("testing")
public class GameDaoDBStubImpl implements GameDao {

    private Game onlyGame;

    public GameDaoDBStubImpl() {

        this.onlyGame = new Game();
        this.onlyGame.setGameId(1);
        this.onlyGame.setAnswer("1234");
        this.onlyGame.setStatus(Util.getGAME_STATUS_IN_PROGRESS());
        this.onlyGame.setAnswer("1235");

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
        System.out.println("stub fbid");
        if (id == onlyGame.getGameId()) {
            System.out.println("1");
            return onlyGame;
        } else {
            System.out.println("2");
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
