package com.example.horseracing2.controllers;

import com.example.horseracing2.DTO.UserHistory;
import com.example.horseracing2.DTO.UserWallet;
import com.example.horseracing2.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService svc;

    public UserController(UserService svc) {
        this.svc = svc;
    }

    @GetMapping("/user-history")
    public ResponseEntity<?> history(@RequestParam String email) {
        log.info("GET /user-history?email={}", email);
        try {
            List<UserHistory> h = svc.getUserHistory(email);
            log.debug("Found {} history items for {}", h.size(), email);
            return ResponseEntity.ok(h);
        } catch (EmptyResultDataAccessException e) {
            log.error("User not found for history", e);
            return ResponseEntity.status(404).body(Map.of("error", "User not found"));
        }
    }

    @GetMapping("/user-wallet")
    public ResponseEntity<?> wallet(@RequestParam String email) {
        log.info("GET /user-wallet?email={}", email);
        try {
            UserWallet w = svc.getUserWallet(email);
            log.debug("Wallet for {} = {}", email, w.getWallet());
            return ResponseEntity.ok(w);
        } catch (EmptyResultDataAccessException e) {
            log.error("User not found for wallet", e);
            return ResponseEntity.status(404).body(Map.of("error", "User not found"));
        }
    }
}
