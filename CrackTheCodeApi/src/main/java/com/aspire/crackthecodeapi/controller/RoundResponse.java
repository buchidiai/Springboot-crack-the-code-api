/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.controller;

import java.time.LocalDateTime;

/**
 *
 * @author louie
 */
public class RoundResponse {

    private int roundId;
    private int roundNumber;
    private int gameId;
    private LocalDateTime time;
    private String result;

    public RoundResponse(int roundId, int roundNumber, int gameId, LocalDateTime time, String result) {
        this.roundId = roundId;
        this.roundNumber = roundNumber;
        this.gameId = gameId;
        this.time = time;
        this.result = result;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
