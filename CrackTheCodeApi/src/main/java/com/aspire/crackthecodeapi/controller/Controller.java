/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.controller;

import com.aspire.crackthecodeapi.models.Game;
import com.aspire.crackthecodeapi.models.Round;
import com.aspire.crackthecodeapi.service.ServiceLayer;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
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

    private static final String GAME_IN_PROGRESS = "in-Progress";
    private static final String FINISHED_GAME = "finished";

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

    @RequestMapping(value = "/guess", method = RequestMethod.POST)
    public ResponseEntity<Round> guessAnswer(@RequestBody Game game) {

        ResponseEntity response = new ResponseEntity(null, HttpStatus.NO_CONTENT);

        //check check if values are unique or guess length or contains alphabet
        if (!(isUnique(game.getGuess())) || game.getGuess().length() != 4 || !game.getGuess().matches("[0-9]+")) {

            String clientResponse = "{\n gameId: " + game.getGameId() + ", \n"
                    + " Error: guess must be a 4 digit unique number. \n}";

            response = new ResponseEntity(clientResponse, HttpStatus.OK);
        } else {

            //calculate round
            Round currentRound = service.calculatedResult(game);

            System.out.println("currentRound " + currentRound);

            //null is returned if game is finished
            if (currentRound == null) {
                System.out.println("1");
                //create fake json object
                String clientResponse = "{\n gameId: " + game.getGameId() + ", \n"
                        + " status: completed \n}";

                response = new ResponseEntity(clientResponse, HttpStatus.OK);
                //check if status has been set to finished and send result to client
            } else if (currentRound.getStatus().equals(FINISHED_GAME)) {
                System.out.println("2");

                response = new ResponseEntity(currentRound, HttpStatus.OK);

            } else if (currentRound.getStatus().equals(GAME_IN_PROGRESS)) {
                System.out.println("3");
                response = new ResponseEntity(currentRound, HttpStatus.OK);

            }
            System.out.println("5");
        }

        System.out.println("5");

        return response;
    }

    public static boolean isUnique(String input) { // Create a Set to insert characters
        Set<Character> set = new HashSet<>();
        // get all characters form String
        char[] characters = input.toCharArray();
        for (Character c : characters) {
            if (!set.add(c)) {
                return false;
            }
        }
        return true;
    }

}
