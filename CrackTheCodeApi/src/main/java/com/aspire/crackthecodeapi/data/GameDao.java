/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.data;

import com.aspire.crackthecodeapi.models.Game;
import com.aspire.crackthecodeapi.models.Round;
import java.util.List;

/**
 *
 * @author louie
 */
public interface GameDao {

    Game CreateGame(Game game);

    Game findGamebyId(int id);

    boolean updateGame(Game game, Round round);

    List<Game> getAllGames();

}
