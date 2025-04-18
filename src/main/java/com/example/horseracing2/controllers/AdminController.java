package com.example.horseracing2.controllers;

import com.example.horseracing2.models.HistoryAdmin;
import com.example.horseracing2.services.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {
    private final AdminService svc;

    public AdminController(AdminService svc) {
        this.svc = svc;
    }

    @GetMapping("/admin-history")
    public List<HistoryAdmin> getHistory() {
        return svc.getAdminHistory();
    }
}
