package com.example.horseracing2.services;

import com.example.horseracing2.DTO.Race;
import com.example.horseracing2.DTO.Horse;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RaceHorseServiceTest {
    @Test
    void getAllRaces() {
        JdbcTemplate jdbc = mock(JdbcTemplate.class);
        Race race = new Race(1, new Timestamp(0), new Timestamp(0), "loc", new Timestamp(0));
        when(jdbc.query(anyString(), any(RowMapper.class))).thenReturn(List.of(race));
        RaceHorseService svc = new RaceHorseService(jdbc);
        List<Race> list = svc.getAllRaces();
        assertEquals(1, list.size());
        assertEquals(race, list.get(0));
    }
    @Test
    void getAllHorses() {
        JdbcTemplate jdbc = mock(JdbcTemplate.class);
        Horse horse = new Horse(1, "name", 5, "breed", "rider", new Timestamp(0));
        when(jdbc.query(anyString(), any(RowMapper.class))).thenReturn(List.of(horse));
        RaceHorseService svc = new RaceHorseService(jdbc);
        List<Horse> list = svc.getAllHorses();
        assertEquals(1, list.size());
        assertEquals(horse, list.get(0));
    }
}