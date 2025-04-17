package com.example.horseracing2.controllers;

import com.example.horseracing2.DTO.MakeBetRequest;
import com.example.horseracing2.DTO.UpdateBetRequest;
import com.example.horseracing2.services.PlacedBetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PlacedBetController {

    private static final Logger log = LoggerFactory.getLogger(PlacedBetController.class);
    private final PlacedBetService svc;

    public PlacedBetController(PlacedBetService svc) {
        this.svc = svc;
    }

    @PostMapping("/make-bet")
    public ResponseEntity<?> makeBet(@RequestBody MakeBetRequest req) {
        log.info("POST /make-bet payload={}", req);
        try {
            svc.makeBet(req);
            log.debug("Bet placed successfully for email={}", req.getEmail());
            return ResponseEntity.ok(Map.of("message", "Bet placed successfully"));
        } catch (IllegalArgumentException e) {
            log.warn("makeBet failed: {}", e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (EmptyResultDataAccessException e) {
            log.error("makeBet: user or bet not found", e);
            return ResponseEntity.status(404).body(Map.of("error", "User or bet not found"));
        } catch (Exception e) {
            log.error("Exception in makeBet", e);
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/edit-bet")
    public ResponseEntity<?> updateBet(@RequestBody UpdateBetRequest req) {
        log.info("POST /edit-bet payload={}", req);
        try {
            boolean ok = svc.updatePlacedBetState(req.getPlacedBetId(), req.getNewState());
            if (!ok) {
                log.warn("No rows updated for placedBetId={}", req.getPlacedBetId());
                return ResponseEntity.status(404).body(Map.of("error", "Bet not found or not updated"));
            }
            return ResponseEntity.ok(Map.of("success", true));
        } catch (EmptyResultDataAccessException e) {
            log.error("updateBet: bet not found", e);
            return ResponseEntity.status(404).body(Map.of("error", "Bet not found"));
        } catch (Exception e) {
            log.error("Exception in updateBet", e);
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }
}
