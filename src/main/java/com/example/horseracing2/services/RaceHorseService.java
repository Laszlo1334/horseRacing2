package com.example.horseracing2.services;

import com.example.horseracing2.models.Horse;
import com.example.horseracing2.models.Race;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaceHorseService {
    private final JdbcTemplate jdbc;

    public RaceHorseService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Race> getAllRaces() {
        String sql = "SELECT id, start_time, end_time, location, create_at FROM races";
        return jdbc.query(sql, (rs, rowNum) -> new Race(
                rs.getInt("id"),
                rs.getTimestamp("start_time"),
                rs.getTimestamp("end_time"),
                rs.getString("location"),
                rs.getTimestamp("create_at")
        ));
    }

    public List<Horse> getAllHorses() {
        String sql = "SELECT id, name, age, breed, rider, create_at FROM horses";
        return jdbc.query(sql, (rs, rowNum) -> new Horse(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("breed"),
                rs.getString("rider"),
                rs.getTimestamp("create_at")
        ));
    }
}
