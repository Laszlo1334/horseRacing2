package com.example.horseracing2.services;

import com.example.horseracing2.models.MakeBetRequest;
import com.example.horseracing2.models.UpdateBetRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Service
public class PlacedBetService {
    private final JdbcTemplate jdbc;

    public PlacedBetService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Transactional
    public void makeBet(MakeBetRequest req) {
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
        if (wallet.compareTo(req.getSum()) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
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
    }

    public boolean updateBet(UpdateBetRequest req) {
        String sql = "UPDATE bets SET race_id = ?, horses_id = ?, multyplier = ?, bet_type = ? WHERE id = ?";
        return jdbc.update(
                sql,
                req.getRaceId(),
                req.getHorsesId(),
                req.getMultiplier(),
                req.getBetType(),
                req.getId()
        ) > 0;
    }
}
