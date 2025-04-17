package com.example.horseracing2.services;

import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlacedBetServiceTest {
    @Test
    void updatePlacedBetStateTrue() {
        JdbcTemplate jdbc = mock(JdbcTemplate.class);
        when(jdbc.update(anyString(), eq("WIN"), eq(1))).thenReturn(1);
        PlacedBetService svc = new PlacedBetService(jdbc);
        assertTrue(svc.updatePlacedBetState(1, "WIN"));
    }
    @Test
    void updatePlacedBetStateFalse() {
        JdbcTemplate jdbc = mock(JdbcTemplate.class);
        when(jdbc.update(anyString(), eq("LOSE"), eq(2))).thenReturn(0);
        PlacedBetService svc = new PlacedBetService(jdbc);
        assertFalse(svc.updatePlacedBetState(2, "LOSE"));
    }
}