package com.example.horseracing2.controllers;

import com.example.horseracing2.models.AddBetRequest;
import com.example.horseracing2.models.BetListDto;
import com.example.horseracing2.models.EditBetRequest;
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

    @PostMapping("/edit-bet")
    public ResponseEntity<?> editBet(@RequestBody EditBetRequest req) {
        boolean ok = svc.editBetState(req);
        if (!ok) return ResponseEntity.status(404).body(Map.of("error", "Bet not found"));
        return ResponseEntity.ok(Map.of("message", "Bet updated successfully"));
    }

    @GetMapping("/list-bets")
    public List<BetListDto> listBets() {
        return svc.listBets();
    }
}
