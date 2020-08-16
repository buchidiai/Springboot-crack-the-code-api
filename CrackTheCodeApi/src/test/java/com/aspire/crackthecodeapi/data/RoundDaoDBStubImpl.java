/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.data;

import com.aspire.crackthecodeapi.models.Round;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author louie
 */
@Profile("testing")
public class RoundDaoDBStubImpl implements RoundDao {

    private Round onlyRound;
    private final LocalDateTime now = LocalDateTime.now();
    private static final String GAME_IN_PROGRESS = "in-Progress";

    public RoundDaoDBStubImpl() {

        onlyRound = new Round();

        onlyRound.setRoundId(0);
        onlyRound.setGameId(1);
        onlyRound.setRoundNumber(1);
        onlyRound.setTime(now);
        onlyRound.setExact(3);
        onlyRound.setPartial(0);
        onlyRound.setStatus(GAME_IN_PROGRESS);
    }

    @Override
    public boolean createRound(int roundNumber, int gameId) {
        if (roundNumber == onlyRound.getRoundNumber() && onlyRound.getGameId() == gameId) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteRoundByGameId(int gameId) {
        if (onlyRound.getGameId() == gameId) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Round getRoundByGameId(int gameId) {
        if (onlyRound.getGameId() == gameId) {
            return onlyRound;
        } else {
            return null;
        }
    }

    @Override
    public boolean addRound(Round round, int roundNumber, int gameId) {
        if (roundNumber == onlyRound.getRoundNumber() && onlyRound.getGameId() == gameId) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getRoundNumberByGameId(int gameId) {

        if (onlyRound.getGameId() == gameId) {
            return onlyRound.getRoundNumber();

        } else {
            return -1;
        }
    }

    @Override
    public List<Round> getAllRoundsByGameId(int gameId) {

        List<Round> allrounds = new ArrayList<>();

        if (onlyRound.getGameId() == gameId) {

            allrounds.add(onlyRound);

        } else {
            return null;
        }

        return allrounds;

    }

    @Override
    public List<Round> getAllRounds() {
        List<Round> allrounds = new ArrayList<>();
        allrounds.add(onlyRound);
        return allrounds;
    }

}
