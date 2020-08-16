/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.service;

import com.aspire.crackthecodeapi.controller.GameResponse;
import com.aspire.crackthecodeapi.controller.RoundResponse;
import com.aspire.crackthecodeapi.data.GameDao;
import com.aspire.crackthecodeapi.data.RoundDao;
import com.aspire.crackthecodeapi.models.Game;
import com.aspire.crackthecodeapi.models.Round;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author louie
 */
@Service
public class serviceLayerImp implements ServiceLayer {

    @Autowired
    private GameDao gameDb;

    @Autowired
    private RoundDao roundDb;

    private static final String GAME_IN_PROGRESS = "in-Progress";
    private static final String GAME_FINISHED = "finished";
    private static final LocalDateTime now = LocalDateTime.now();

    @Override
    public String generateAnswer() {

        final int size = 10;

        String answer = "";

        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = 0; i <= size; i++) {
            list.add(i);
        }

        Random rand = new Random();
        while (list.size() > 0) {

            int index = rand.nextInt(list.size() - 1);

            answer += list.remove(index);

            list.remove(index);

            if (answer.length() == 4) {

                break;
            }
        }
        return answer;
    }

    @Override
    public Game findGamebyId(int id) {

        return gameDb.findGameByGameId(id);
    }

    @Override
    public List<Game> getAllGames() {

        List<Game> games = gameDb.getAllGames();

        games.stream().filter(g -> (g.getStatus().equals(GAME_IN_PROGRESS))).forEachOrdered(g -> {
            g.setAnswer("N/A");
        });

        return games;
    }

    @Override
    public List<RoundResponse> getAllRoundsByGame(int id) {

        List<Round> rounds = roundDb.getAllRoundsByGameId(id);

        List<Round> sortedRounds = rounds.stream().sorted().collect(Collectors.toList());

        List<RoundResponse> cleanedUpRound = new ArrayList();

        if (!rounds.isEmpty()) {
            sortedRounds.stream().map(r -> {
                return r;
            }).map(r -> new RoundResponse(r.getRoundId(), r.getRoundNumber(), r.getGameId(), r.getTime(), "e:" + r.getExact() + ":p:" + r.getPartial())).forEachOrdered(roundresponse -> {
                cleanedUpRound.add(roundresponse);
            });

        }

        return cleanedUpRound;
    }

    @Override
    public Game createGame(Game game) {
        //create game and save to db
        Game createdGame = gameDb.createGame(game);
        //create round with gameId
        roundDb.createRound(0, createdGame.getGameId());
        return createdGame;

    }

    @Override
    public Game getGame(int gameId) {

        Game g = gameDb.findGameByGameId(gameId);

        if (g.getStatus().equals(GAME_IN_PROGRESS)) {
            g.setAnswer("N/A");

        }

        return g;
    }

    @Override
    public GameResponse calculatedResult(Game game) {

        //game found
        final Game existingGame = gameDb.findGameByGameId(game.getGameId());

        //user guess
        final String guess = game.getGuess();

        //answer
        final String answer = existingGame.getAnswer();

        //getcurrent round played
        final int currentRound = roundDb.getRoundNumberByGameId(existingGame.getGameId());

        //current round
        Round round = null;

        //client response
        GameResponse response = null;

        //check if game is in progress so
        if (existingGame.getStatus().equals(GAME_IN_PROGRESS)) {

            //all digits match
            if (answer.equals(guess)) {

                round = new Round();

                game.setStatus(GAME_FINISHED);

                round.setGameId(game.getGameId());
                round.setGuess(guess);
                round.setTime(now);
                round.setExact(4);
                round.setPartial(0);
                round.setResult("e:" + round.getExact() + ":p:" + round.getPartial());
                round.setRoundNumber(currentRound + 1);
                round.setStatus(GAME_FINISHED);

                //update game table /database
                gameDb.updateGame(game);

                //update round table
                roundDb.addRound(round, currentRound + 1, game.getGameId());

                response = new GameResponse(round.getRoundNumber(), game.getGameId(), guess, now, "e:" + round.getExact() + ":p:" + round.getPartial(), GAME_FINISHED, "Congrats!! You Cracked the Code");

            } else {

                //check which digits match
                char[] answerArray = answer.toCharArray();
                char[] guessArray = guess.toCharArray();
                int ex = 0;
                int p = 0;

                for (int i = 0; i < answer.length(); i++) {

                    for (int j = 0; j < answer.length(); j++) {

                        if (answerArray[i] == guessArray[j]) {

                            if ((i == 0 && j == 0) && answerArray[i] == guessArray[j]) {
                                ex++;
                            } else if ((i == 0 && j > 0) && answerArray[i] == guessArray[j]) {
                                p++;
                            } else if ((i == 1 && j == 1) && answerArray[i] == guessArray[j]) {
                                ex++;
                            } else if (i == 1 && j > 0 && answerArray[i] == guessArray[j]) {
                                p++;
                            } else if (i == 2 && j == 2 && answerArray[i] == guessArray[j]) {
                                ex++;
                            } else if (i == 2 && j > 0 && answerArray[i] == guessArray[j]) {
                                p++;
                            } else if (i == 3 && j == 3 && answerArray[i] == guessArray[j]) {
                                ex++;
                            } else if (i == 3 && j > 0 && answerArray[i] == guessArray[j]) {
                                p++;
                            }

                        }

                    }

                }

                round = new Round();
                game.setStatus(GAME_IN_PROGRESS);

                round.setGameId(game.getGameId());
                round.setGuess(guess);
                round.setTime(now);
                round.setExact(ex);
                round.setPartial(p);
                round.setResult("e:" + ex + ":p:" + p);
                round.setRoundNumber(currentRound + 1);
                round.setStatus(GAME_IN_PROGRESS);

                //update game table /database
                gameDb.updateGame(game);

                //update round table
                roundDb.addRound(round, round.getRoundNumber(), game.getGameId());

                response = new GameResponse(round.getRoundNumber(), game.getGameId(), guess, now, "e:" + round.getExact() + ":p:" + round.getPartial(), GAME_IN_PROGRESS, "Try again! Code needs to be cracked ;)");

            }

        }

        return round == null ? null : response;

    }

}
