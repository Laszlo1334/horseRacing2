package com.example.horseracing2.services;

import com.example.horseracing2.models.HistoryAdmin;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final JdbcTemplate jdbc;

    public AdminService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<HistoryAdmin> getAdminHistory() {
        String sql = ""
                + "SELECT pb.id AS placed_bet_id, b.id AS bet_id, pb.user_id, pb.sum, pb.state, "
                + "       b.multyplier, pb.create_at "
                + "FROM placed_bets pb "
                + "JOIN bets b ON pb.bet_id = b.id";
        return jdbc.query(sql, (rs, rowNum) -> new HistoryAdmin(
                rs.getInt("placed_bet_id"),
                rs.getInt("bet_id"),
                rs.getInt("user_id"),
                rs.getBigDecimal("sum"),
                rs.getString("state"),
                rs.getBigDecimal("multyplier"),
                rs.getTimestamp("create_at")
        ));
    }
}
