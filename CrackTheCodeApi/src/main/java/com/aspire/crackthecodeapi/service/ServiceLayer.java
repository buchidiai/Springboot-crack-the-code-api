/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.service;

import com.aspire.crackthecodeapi.models.Game;
import com.aspire.crackthecodeapi.models.Round;
import java.util.List;

/**
 *
 * @author louie
 */
public interface ServiceLayer {

    String generateAnswer();

    Round calculatedResult(Game game);

    Game createGame(Game game);

    Game findGamebyId(int id);

    List<Game> getAllGames();

    List<Round> getAllRoundsByGame(int id);

}
