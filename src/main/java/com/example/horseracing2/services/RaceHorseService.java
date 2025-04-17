package com.example.horseracing2.services;

import com.example.horseracing2.DTO.Horse;
import com.example.horseracing2.DTO.Race;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaceHorseService {
    private static final Logger log = LoggerFactory.getLogger(RaceHorseService.class);
    private final JdbcTemplate jdbc;

    public RaceHorseService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Race> getAllRaces() {
        log.info("RaceHorseService.getAllRaces called");
        String sql = "SELECT id, start_time, end_time, location, create_at FROM races";
        var list = jdbc.query(sql, (rs, rowNum) -> new Race(
                rs.getInt("id"),
                rs.getTimestamp("start_time"),
                rs.getTimestamp("end_time"),
                rs.getString("location"),
                rs.getTimestamp("create_at")
        ));
        log.debug("Returning {} races", list.size());
        return list;
    }

    public List<Horse> getAllHorses() {
        log.info("RaceHorseService.getAllHorses called");
        String sql = "SELECT id, name, age, breed, rider, create_at FROM horses";
        var list = jdbc.query(sql, (rs, rowNum) -> new Horse(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("breed"),
                rs.getString("rider"),
                rs.getTimestamp("create_at")
        ));
        log.debug("Returning {} horses", list.size());
        return list;
    }
}
