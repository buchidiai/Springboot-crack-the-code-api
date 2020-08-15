/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.models;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author louie
 */
public class Round implements Comparable<Round> {

//    @JsonIgnore
    private int roundId;
    private int roundNumber;
    private int gameId;
    private String guess;
    private LocalDateTime time;
//    @JsonIgnore
    private int exact;
//    @JsonIgnore
    private int partial;
    private String Result;
    private String status;

    public Round(int roundNumber, int gameId, String guess, LocalDateTime time) {
        this.roundNumber = roundNumber;
        this.gameId = gameId;
        this.guess = guess;
        this.time = time;
    }

    public Round() {
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getExact() {
        return exact;
    }

    public void setExact(int exact) {
        this.exact = exact;
    }

    public int getPartial() {
        return partial;
    }

    public void setPartial(int partial) {
        this.partial = partial;
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

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + this.roundNumber;
        hash = 19 * hash + this.gameId;
        hash = 19 * hash + Objects.hashCode(this.guess);
        hash = 19 * hash + Objects.hashCode(this.time);
        hash = 19 * hash + this.exact;
        hash = 19 * hash + this.partial;
        hash = 19 * hash + Objects.hashCode(this.Result);
        hash = 19 * hash + Objects.hashCode(this.status);
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
        if (this.roundNumber != other.roundNumber) {
            return false;
        }
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.exact != other.exact) {
            return false;
        }
        if (this.partial != other.partial) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        if (!Objects.equals(this.Result, other.Result)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Round o) {
        return this.time.compareTo(o.time);
    }

}
