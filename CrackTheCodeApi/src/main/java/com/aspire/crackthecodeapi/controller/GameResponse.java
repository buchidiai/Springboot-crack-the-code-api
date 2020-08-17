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
public class GameResponse {

    private int roundNumber;
    private int gameId;
    private String guess;
    private LocalDateTime time;
    private String Result;
    private String status;
    private String message;

    public GameResponse(int roundNumber, int gameId, String guess, LocalDateTime time, String Result, String status, String message) {
        this.roundNumber = roundNumber;
        this.gameId = gameId;
        this.guess = guess;
        this.time = time;
        this.Result = Result;
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public int getGameId() {
        return gameId;
    }

    public String getGuess() {
        return guess;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getResult() {
        return Result;
    }

    public String getStatus() {
        return status;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "GameResponse{" + "roundNumber=" + roundNumber + ", gameId=" + gameId + ", guess=" + guess + ", time=" + time + ", Result=" + Result + ", status=" + status + ", message=" + message + '}';
    }

}
