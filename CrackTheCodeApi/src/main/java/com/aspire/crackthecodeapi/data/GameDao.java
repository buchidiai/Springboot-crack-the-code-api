/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.data;

import com.aspire.crackthecodeapi.models.Game;
import java.util.List;

/**
 *
 * @author louie
 */
public interface GameDao {

    Game createGame(Game game);

    void deleteGame(int id);

    Game findGamebyId(int id);

    void updateGame(Game game);

    List<Game> getAllGames();

}
