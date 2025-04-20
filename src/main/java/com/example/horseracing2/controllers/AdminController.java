package com.example.horseracing2.controllers;

import com.example.horseracing2.DTO.AddBetRequest;
import com.example.horseracing2.DTO.HistoryAdmin;
import com.example.horseracing2.services.AdminService;
import com.example.horseracing2.repositories.RaceRepository;
import com.example.horseracing2.repositories.HorseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    private final AdminService adminSvc;
    private final RaceRepository raceRepo;
    private final HorseRepository horseRepo;
    private final JdbcTemplate jdbc;

    public AdminController(AdminService adminSvc,
                           RaceRepository raceRepo,
                           HorseRepository horseRepo,
                           JdbcTemplate jdbc) {
        this.adminSvc = adminSvc;
        this.raceRepo = raceRepo;
        this.horseRepo = horseRepo;
        this.jdbc = jdbc;
    }

    @GetMapping({"/api/admin/history", "/admin-history"})
    public List<HistoryAdmin> getAdminHistory() {
        log.info("GET /admin-history called");
        List<HistoryAdmin> list = adminSvc.getAdminHistory();
        log.debug("Returning {} history items", list.size());
        return list;
    }

    @GetMapping("/add-bet-form")
    public Map<String, List<?>> getAddBetForm() {
        log.info("GET /add-bet-form called");
        var races  = raceRepo.findAll();
        var horses = horseRepo.findAll();
        log.debug("Fetched races={}, horses={}", races.size(), horses.size());
        return Map.of("races", races, "horses", horses);
    }

    @PostMapping("/add-bet")
    public ResponseEntity<?> addBet(@RequestBody AddBetRequest req) {
        log.info("POST /add-bet payload={}", req);
        try {
            int rows = jdbc.update(
                    "INSERT INTO bets (race_id, horses_id, multyplier, bet_type, create_at) VALUES (?, ?, ?, ?, ?)",
                    req.getRaceId(),
                    req.getHorseId(),
                    req.getMultiplier(),
                    req.getBetType(),
                    new Timestamp(System.currentTimeMillis())
            );
            log.debug("Inserted {} rows into bets", rows);
            if (rows == 0) {
                log.error("No rows inserted for add-bet");
                return ResponseEntity.status(500).body(Map.of("error", "Не вдалося додати ставку"));
            }
            return ResponseEntity.ok(Map.of("success", true));
        } catch (Exception e) {
            log.error("Exception in addBet", e);
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }
}
