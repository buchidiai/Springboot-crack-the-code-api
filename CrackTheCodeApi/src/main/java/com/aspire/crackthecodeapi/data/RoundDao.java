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

    boolean createRound(int roundNumber, int gameId);

    boolean deleteRoundByGameId(int gameId);

    Round getRoundByGameId(int gameId);

    boolean addRound(Round round, int roundNumber, int gameId);

    int getRoundNumberByGameId(int gameId);

    List<Round> getAllRoundsByGameId(int gameId);

    List<Round> getAllRounds();

}
