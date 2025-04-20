package com.example.horseracing2.controllers;

import com.example.horseracing2.DTO.UserHistory;
import com.example.horseracing2.DTO.UserWallet;
import com.example.horseracing2.services.UserService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    private final UserService svc;

    public UserController(UserService svc) {
        this.svc = svc;
    }

    @GetMapping("/user-history")
    public ResponseEntity<?> history(@RequestParam String email) {
        try {
            List<UserHistory> h = svc.getUserHistory(email);
            return ResponseEntity.ok(h);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(404).body(Map.of("error", "User not found"));
        }
    }

    @GetMapping("/user-wallet")
    public ResponseEntity<?> wallet(@RequestParam String email) {
        try {
            UserWallet w = svc.getUserWallet(email);
            return ResponseEntity.ok(w);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(404).body(Map.of("error", "User not found"));
        }
    }
}
