/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.service;

import com.aspire.crackthecodeapi.data.Dao;
import com.aspire.crackthecodeapi.models.Game;
import com.aspire.crackthecodeapi.models.Round;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author louie
 */
@Service
public class serviceLayerImp implements ServiceLayer {

    @Autowired
    private Dao db;

    @Override
    public String generateAnswer() {

        int size = 9;

        String answer = "";

        ArrayList<Integer> list = new ArrayList<Integer>(size);
        for (int i = 0; i <= size; i++) {
            list.add(i);
        }

        Random rand = new Random();
        while (list.size() > 0) {

            int index = rand.nextInt(list.size() - 1);

            answer += list.remove(index);

            list.remove(index);

            if (answer.length() == 4) {

                break;
            }
        }
        return answer;
    }

    @Override
    public Game findGamebyId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Game> getAllGames() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Round> getAllRoundsByGame(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Game createGame(Game game) {
        Game createdGame = db.CreateGame(game);
        return createdGame;

    }

    @Override
    public Round calculatedResult(Game game) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
