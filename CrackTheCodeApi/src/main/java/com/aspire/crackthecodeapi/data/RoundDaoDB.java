/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.data;

import com.aspire.crackthecodeapi.models.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author louie
 */
@Repository
@Profile("database")
public class RoundDaoDB implements RoundDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public boolean createRound(int roundNumber, int gameId) {

        final String INSERT_ROUND = "INSERT INTO round (roundNumber, game_gameId) VALUES(?,?)";

        return jdbc.update(INSERT_ROUND,
                roundNumber,
                gameId) > 0;
    }

    @Override
    public int getRoundNumberByGameId(int gameId) {

        final String SELECT_FROM_ROUND_TABLE = "SELECT MAX(RoundNumber) FROM round WHERE game_gameId = ?";

        int maxRound = jdbc.queryForObject(SELECT_FROM_ROUND_TABLE, new Object[]{gameId}, Integer.class);

        return maxRound;
    }

    @Override
    public List<Round> getAllRoundsByGameId(int gameId) {

        final String SELECT_FROM_ROUND_TABLE = "SELECT * FROM round WHERE game_gameId = ?";

        List<Round> roundsByGameId = jdbc.query(SELECT_FROM_ROUND_TABLE, new RoundMapper(), gameId);

        return roundsByGameId;

    }

    @Override
    public boolean addRound(Round round, int roundNumber, int gameId) {

        final String ADD_ROUND = "INSERT INTO round (roundNumber,guessTime, partial, exact, game_gameId ) VALUES(?,?,?,?,?);";
        return jdbc.update(ADD_ROUND, roundNumber, round.getTime(), round.getPartial(), round.getExact(), gameId) > 0;
    }

    @Override
    public Round getRoundByGameId(int gameId) {

        final String SELECT_FROM_ROUND_TABLE = "SELECT * FROM round WHERE game_gameId = ?";

        Round foundRound = jdbc.queryForObject(SELECT_FROM_ROUND_TABLE, new RoundMapper(), gameId);

        return foundRound;

    }

    @Override
    public boolean deleteRoundByGameId(int gameId) {
        final String DELETE_ROUND = "DELETE FROM round "
                + "WHERE game_gameId = ?";
        return jdbc.update(DELETE_ROUND, gameId) > 0;
    }

    @Override
    public List<Round> getAllRounds() {
        final String SELECT_FROM_ROUND_TABLE = "SELECT * FROM round;";

        List<Round> rounds = jdbc.query(SELECT_FROM_ROUND_TABLE, new RoundMapper());

        return rounds;
    }

    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundId(rs.getInt("roundId"));
            round.setRoundNumber(rs.getInt("roundNumber"));
            round.setTime(rs.getTimestamp("guessTime") == null ? null : rs.getTimestamp("guessTime").toLocalDateTime());
            round.setPartial(rs.getInt("partial"));
            round.setExact(rs.getInt("exact"));
            round.setGameId(rs.getInt("game_gameId"));
            return round;
        }

    }

}
