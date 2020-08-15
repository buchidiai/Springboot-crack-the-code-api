/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.controller;

/**
 *
 * @author louie
 */
public class CreateResponse {

    int gameId;
    String message;

    public CreateResponse(int gameId, String message) {
        this.gameId = gameId;
        this.message = message;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
