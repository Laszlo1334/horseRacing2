package com.example.horseracing2.controllers;

import com.example.horseracing2.services.RaceHorseService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FormController {
    private final RaceHorseService svc;

    public FormController(RaceHorseService svc) {
        this.svc = svc;
    }

    @GetMapping("/add-bet-form")
    public Map<String, List<?>> getForm() {
        Map<String, List<?>> m = new HashMap<>();
        m.put("races", svc.getAllRaces());
        m.put("horses", svc.getAllHorses());
        return m;
    }
}
