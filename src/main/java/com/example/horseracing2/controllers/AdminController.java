package com.example.horseracing2.controllers;

import com.example.horseracing2.DTO.HistoryAdmin;
import com.example.horseracing2.services.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    private final AdminService svc;

    public AdminController(AdminService svc) {
        this.svc = svc;
    }

    @GetMapping({"/api/admin/history", "/admin-history"})
    public List<HistoryAdmin> getAdminHistory() {
        return svc.getAdminHistory();
    }
}
