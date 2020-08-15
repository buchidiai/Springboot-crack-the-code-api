/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.service;

import com.aspire.crackthecodeapi.data.GameDao;
import com.aspire.crackthecodeapi.data.RoundDao;
import com.aspire.crackthecodeapi.models.Game;
import com.aspire.crackthecodeapi.models.Round;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    @Override
    public String generateAnswer() {

        final int size = 10;

        String answer = "";

        ArrayList<Integer> list = new ArrayList<Integer>(size);
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

        return gameDb.findGamebyId(id);
    }

    @Override
    public List<Game> getAllGames() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Round> getAllRoundsByGame(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Game createGame(Game game) {
        //create game and save to db
        Game createdGame = gameDb.CreateGame(game);
        //create round with gameId
        roundDb.createRound(0, createdGame.getGameId());
        return createdGame;

    }

    @Override
    public Round calculatedResult(Game game) {

        final Game existingGame = gameDb.findGamebyId(game.getGameId());

        final String guess = game.getGuess();

        final String answer = existingGame.getAnswer();

        final LocalDateTime now = LocalDateTime.now();

        final int currentRound = roundDb.getRoundNumber(existingGame.getGameId());

        Round round = null;

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
                gameDb.updateGame(game, round);

                //update round table
                roundDb.updateRound(round, currentRound + 1, game.getGameId());

            } else {

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
                gameDb.updateGame(game, round);

                //update round table
                roundDb.updateRound(round, round.getRoundNumber(), game.getGameId());

            }

        }

        return round;

    }

}
