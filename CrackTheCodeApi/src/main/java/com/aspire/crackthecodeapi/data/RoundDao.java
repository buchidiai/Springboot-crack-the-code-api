/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.data;

import com.aspire.crackthecodeapi.models.Round;
import java.util.List;

/**
 *
 * @author louie
 */
public interface RoundDao {

    void createRound(int roundNumber, int gameId);

    Round getRound(int gameId);

    void addRound(Round round, int roundNumber, int gameId);

    int getRoundNumber(int gameId);

    List<Round> getAllRoundsByGame(int id);

}
