package com.example.horseracing2.services;

import com.example.horseracing2.DTO.UserHistory;
import com.example.horseracing2.DTO.UserWallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final JdbcTemplate jdbc;

    public UserService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<UserHistory> getUserHistory(String email) {
        log.info("UserService.getUserHistory called for email={}", email);
        Integer userId = jdbc.queryForObject(
                "SELECT id FROM users WHERE email = ?",
                (rs, rn) -> rs.getInt("id"),
                email
        );
        log.debug("Found userId={}", userId);
        String sql = """
            SELECT pb.id AS placed_bet_id, pb.user_id, b.id AS bet_id, r.id AS race_id,
                   r.start_time, r.end_time, r.location,
                   h.id AS horse_id, h.name AS horse_name, h.age, h.breed, h.rider,
                   b.multyplier, b.bet_type, pb.sum, pb.state, pb.create_at
            FROM placed_bets pb
            JOIN bets b ON pb.bet_id = b.id
            JOIN races r ON b.race_id = r.id
            JOIN horses h ON b.horses_id = h.id
            WHERE pb.user_id = ?
        """;
        List<UserHistory> list = jdbc.query(sql, (rs, rowNum) -> new UserHistory(
                rs.getInt("placed_bet_id"),
                rs.getInt("user_id"),
                rs.getInt("bet_id"),
                rs.getInt("race_id"),
                rs.getTimestamp("start_time"),
                rs.getTimestamp("end_time"),
                rs.getString("location"),
                rs.getInt("horse_id"),
                rs.getString("horse_name"),
                rs.getInt("age"),
                rs.getString("breed"),
                rs.getString("rider"),
                rs.getBigDecimal("multyplier"),
                rs.getString("bet_type"),
                rs.getBigDecimal("sum"),
                rs.getString("state"),
                rs.getTimestamp("create_at")
        ), userId);
        log.debug("Returning {} history items for {}", list.size(), email);
        return list;
    }

    public UserWallet getUserWallet(String email) {
        log.info("UserService.getUserWallet called for email={}", email);
        try {
            UserWallet w = jdbc.queryForObject(
                    "SELECT id, email, wallet, create_at FROM users WHERE email = ?",
                    (rs, rn) -> new UserWallet(
                            rs.getInt("id"),
                            rs.getString("email"),
                            rs.getBigDecimal("wallet"),
                            rs.getTimestamp("create_at")
                    ),
                    email
            );
            log.debug("Returning wallet {} for {}", w.getWallet(), email);
            return w;
        } catch (EmptyResultDataAccessException e) {
            log.error("getUserWallet: user not found", e);
            throw e;
        }
    }
}
