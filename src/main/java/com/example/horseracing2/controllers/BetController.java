package com.example.horseracing2.controllers;

import com.example.horseracing2.DTO.BetListDto;
import com.example.horseracing2.services.BetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BetController {
    private static final Logger log = LoggerFactory.getLogger(BetController.class);
    private final BetService svc;

    public BetController(BetService svc) {
        this.svc = svc;
    }

    @GetMapping("/list-bets")
    public List<BetListDto> listBets() {
        log.info("GET /list-bets called");
        List<BetListDto> list = svc.listBets();
        log.debug("Returning {} bets", list.size());
        return list;
    }
}
