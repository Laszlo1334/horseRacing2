package com.example.horseracing2.controllers;

import com.example.horseracing2.models.MakeBetRequest;
import com.example.horseracing2.models.UpdateBetRequest;
import com.example.horseracing2.services.PlacedBetService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PlacedBetController {
    private final PlacedBetService svc;

    public PlacedBetController(PlacedBetService svc) {
        this.svc = svc;
    }

    @PostMapping("/make-bet")
    public ResponseEntity<?> makeBet(@RequestBody MakeBetRequest req) {
        try {
            svc.makeBet(req);
            return ResponseEntity.ok(Map.of("message", "Bet placed successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(404).body(Map.of("error", "User or bet not found"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/update-bet")
    public ResponseEntity<?> updateBet(@RequestBody UpdateBetRequest req) {
        boolean ok = svc.updateBet(req);
        if (!ok) return ResponseEntity.status(404).body(Map.of("error", "Bet not found or not updated"));
        return ResponseEntity.ok(Map.of("success", true));
    }
}
