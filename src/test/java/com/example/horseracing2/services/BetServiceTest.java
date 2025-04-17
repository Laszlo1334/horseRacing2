package com.example.horseracing2.services;

import com.example.horseracing2.DTO.AddBetRequest;
import com.example.horseracing2.DTO.BetListDto;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BetServiceTest {
    @Test
    void addBet() {
        JdbcTemplate jdbc = mock(JdbcTemplate.class);
        BetService svc = new BetService(jdbc);
        AddBetRequest req = new AddBetRequest();
        req.setRaceId(1);
        req.setHorseId(2);
        req.setMultiplier(new BigDecimal("1.2"));
        req.setBetType("win");
        svc.addBet(req);
        verify(jdbc).update(anyString(), eq(1), eq(2), eq(new BigDecimal("1.2")), eq("win"));
    }
    @Test
    void listBets() {
        JdbcTemplate jdbc = mock(JdbcTemplate.class);
        BetListDto dto = new BetListDto(1,1,new Timestamp(0),new Timestamp(0),"loc",1,"n",1,"b","r",new BigDecimal("1.0"),"t");
        when(jdbc.query(anyString(), any(RowMapper.class))).thenReturn(List.of(dto));
        BetService svc = new BetService(jdbc);
        List<BetListDto> list = svc.listBets();
        assertEquals(1, list.size());
        assertEquals(dto, list.get(0));
    }
}