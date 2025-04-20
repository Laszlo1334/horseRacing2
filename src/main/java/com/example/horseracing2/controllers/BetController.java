package com.example.horseracing2.controllers;

import com.example.horseracing2.DTO.AddBetRequest;
import com.example.horseracing2.DTO.BetListDto;
import com.example.horseracing2.services.BetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BetController {
    private final BetService svc;

    public BetController(BetService svc) {
        this.svc = svc;
    }

    @PostMapping("/add-bet")
    public ResponseEntity<?> addBet(@RequestBody AddBetRequest req) {
        svc.addBet(req);
        return ResponseEntity.ok(Map.of("message", "Bet added successfully"));
    }

    @GetMapping("/list-bets")
    public List<BetListDto> listBets() {
        return svc.listBets();
    }
}
