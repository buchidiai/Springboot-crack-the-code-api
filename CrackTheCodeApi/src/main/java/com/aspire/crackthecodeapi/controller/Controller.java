/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.controller;

import com.aspire.crackthecodeapi.models.Game;
import com.aspire.crackthecodeapi.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author louie
 */
@RestController
@RequestMapping("/api/crackthecode")
public class Controller {

    @Autowired
    private ServiceLayer service;

    @RequestMapping(value = "/begin", method = RequestMethod.POST)
    public ResponseEntity createGame() {

        ResponseEntity response = new ResponseEntity(null, HttpStatus.NO_CONTENT);

        //generate answer
        String answer = service.generateAnswer();

        //set status
        final String status = "In-progress";

        //create new game object
        Game game = new Game();
        //set answer
        game.setAnswer(answer);
        //set status
        game.setStatus(status);

        //create game
        Game createdGame = service.createGame(game);

        //set object/response if not null
        if (createdGame != null) {

            //create fake json object
            String clientResponse = "{\n gameId: " + createdGame.getGameId() + ", \n"
                    + " success: " + true + "\n}";
            //set response to client
            response = new ResponseEntity(clientResponse, HttpStatus.CREATED);

        }

        return response;
    }

}
