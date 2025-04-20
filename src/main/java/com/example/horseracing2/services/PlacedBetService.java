package com.example.horseracing2.services;

import com.example.horseracing2.DTO.MakeBetRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Service
public class PlacedBetService {
    private static final Logger log = LoggerFactory.getLogger(PlacedBetService.class);
    private final JdbcTemplate jdbc;

    public PlacedBetService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Transactional
    public void makeBet(MakeBetRequest req) {
        log.info("PlacedBetService.makeBet called with {}", req);
        Integer userId = jdbc.queryForObject(
                "SELECT id FROM users WHERE email = ?",
                (rs, rn) -> rs.getInt("id"),
                req.getEmail()
        );
        BigDecimal wallet = jdbc.queryForObject(
                "SELECT wallet FROM users WHERE email = ?",
                (rs, rn) -> rs.getBigDecimal("wallet"),
                req.getEmail()
        );
        log.debug("User {} wallet balance: {}", userId, wallet);
        if (wallet.compareTo(req.getSum()) < 0) {
            log.warn("Insufficient funds userId={} wallet={} requested={}", userId, wallet, req.getSum());
            throw new IllegalArgumentException("Недостатньо коштів");
        }
        jdbc.queryForObject(
                "SELECT id FROM bets WHERE id = ?",
                (rs, rn) -> rs.getInt("id"),
                req.getBetId()
        );
        jdbc.update(
                "INSERT INTO placed_bets (bet_id, user_id, state, sum, create_at) VALUES (?, ?, ?, ?, ?)",
                req.getBetId(), userId, "ACTIVE", req.getSum(), new Timestamp(System.currentTimeMillis())
        );
        jdbc.update(
                "UPDATE users SET wallet = wallet - ? WHERE id = ?",
                req.getSum(), userId
        );
        log.info("Bet placed for userId={}, betId={}", userId, req.getBetId());
    }

    @Transactional
    public boolean updatePlacedBetState(int placedBetId, String newState) {
        log.info("PlacedBetService.updatePlacedBetState called id={} newState={}", placedBetId, newState);
        int updated = jdbc.update(
                "UPDATE placed_bets SET state = ? WHERE id = ?",
                newState, placedBetId
        );
        log.debug("Rows updated in placed_bets: {}", updated);
        return updated > 0;
    }
}
