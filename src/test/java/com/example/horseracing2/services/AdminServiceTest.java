package com.example.horseracing2.services;

import com.example.horseracing2.DTO.HistoryAdmin;
import com.example.horseracing2.entities.PlacedBet;
import com.example.horseracing2.entities.Bet;
import com.example.horseracing2.entities.User;
import com.example.horseracing2.repositories.PlacedBetRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminServiceTest {
    @Test
    void getAdminHistory() {
        PlacedBetRepository repo = mock(PlacedBetRepository.class);
        PlacedBet pb = new PlacedBet();
        pb.setId(1);
        Bet bet = new Bet(); bet.setId(2); bet.setMultyplier(new BigDecimal("1.5")); pb.setBet(bet);
        User user = new User(); user.setId(3); pb.setUser(user);
        pb.setSum(new BigDecimal("100.00")); pb.setState("WIN"); pb.setCreateAt(new Timestamp(1000));
        when(repo.findAll()).thenReturn(List.of(pb));
        AdminService svc = new AdminService(repo);
        List<HistoryAdmin> history = svc.getAdminHistory();
        assertEquals(1, history.size());
        HistoryAdmin h = history.get(0);
        assertEquals(1, h.getPlacedBetId());
        assertEquals(2, h.getBetId());
        assertEquals(3, h.getUserId());
        assertEquals(new BigDecimal("100.00"), h.getSum());
        assertEquals("WIN", h.getState());
        assertEquals(new BigDecimal("1.5"), h.getMultiplier());
        assertEquals(new Timestamp(1000), h.getCreateAt());
    }
}