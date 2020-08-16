/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.models;

import java.util.Objects;

/**
 *
 * @author louie
 */
public class Game {

    private int gameId;
    private String guess;
    private String answer;
    private String status;

    public Game(int gameId, String guess, String answer, String status) {
        this.gameId = gameId;
        this.guess = guess;
        this.answer = answer;
        this.status = status;
    }

    public Game() {
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.gameId;
        hash = 13 * hash + Objects.hashCode(this.guess);
        hash = 13 * hash + Objects.hashCode(this.answer);
        hash = 13 * hash + Objects.hashCode(this.status);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.answer, other.answer)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Game{" + "gameId=" + gameId + ", guess=" + guess + ", answer=" + answer + ", status=" + status + '}';
    }

}
