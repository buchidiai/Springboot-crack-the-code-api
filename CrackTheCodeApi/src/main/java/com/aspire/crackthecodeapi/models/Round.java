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
public class Round {

    private String roundNumber;
    private int gameId;
    private String guess;
    private String time;
    private String partial;
    private String exact;

    public Round(String roundNumber, int gameId, String guess, String time, String partial, String exact) {
        this.roundNumber = roundNumber;
        this.gameId = gameId;
        this.guess = guess;
        this.time = time;
        this.partial = partial;
        this.exact = exact;
    }

    public String getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(String roundNumber) {
        this.roundNumber = roundNumber;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPartial() {
        return partial;
    }

    public void setPartial(String partial) {
        this.partial = partial;
    }

    public String getExact() {
        return exact;
    }

    public void setExact(String exact) {
        this.exact = exact;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.roundNumber);
        hash = 37 * hash + this.gameId;
        hash = 37 * hash + Objects.hashCode(this.guess);
        hash = 37 * hash + Objects.hashCode(this.time);
        hash = 37 * hash + Objects.hashCode(this.partial);
        hash = 37 * hash + Objects.hashCode(this.exact);
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
        final Round other = (Round) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (!Objects.equals(this.roundNumber, other.roundNumber)) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        if (!Objects.equals(this.partial, other.partial)) {
            return false;
        }
        if (!Objects.equals(this.exact, other.exact)) {
            return false;
        }
        return true;
    }

}
