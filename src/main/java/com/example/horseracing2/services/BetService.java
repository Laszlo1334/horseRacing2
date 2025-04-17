package com.example.horseracing2.services;

import com.example.horseracing2.DTO.AddBetRequest;
import com.example.horseracing2.DTO.BetListDto;
import com.example.horseracing2.DTO.EditBetRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetService {
    private static final Logger log = LoggerFactory.getLogger(BetService.class);
    private final JdbcTemplate jdbc;

    public BetService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void addBet(AddBetRequest req) {
        log.info("BetService.addBet called with {}", req);
        String sql = "INSERT INTO bets (race_id, horses_id, multyplier, bet_type, create_at) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";
        int rows = jdbc.update(sql,
                req.getRaceId(),
                req.getHorseId(),
                req.getMultiplier(),
                req.getBetType()
        );
        log.debug("Inserted {} rows into bets", rows);
    }

    public boolean editBetState(EditBetRequest req) {
        log.info("BetService.editBetState called with {}", req);
        String sql = "UPDATE placed_bets SET state = ? WHERE id = ?";
        int updated = jdbc.update(sql, req.getNewState(), req.getPlacedBetId());
        log.debug("Updated {} rows in placed_bets", updated);
        return updated > 0;
    }

    public List<BetListDto> listBets() {
        log.info("BetService.listBets called");
        String sql = """
            SELECT b.id AS bet_id, r.id AS race_id, r.start_time, r.end_time, r.location,
                   h.id AS horse_id, h.name AS horse_name, h.age, h.breed, h.rider,
                   b.multyplier, b.bet_type
            FROM bets b
            JOIN races r ON b.race_id = r.id
            JOIN horses h ON b.horses_id = h.id
        """;
        var list = jdbc.query(sql, (rs, rowNum) -> new BetListDto(
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
                rs.getString("bet_type")
        ));
        log.debug("BetService.listBets returning {} items", list.size());
        return list;
    }
}
