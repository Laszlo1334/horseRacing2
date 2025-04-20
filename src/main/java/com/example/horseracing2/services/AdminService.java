package com.example.horseracing2.services;

import com.example.horseracing2.DTO.HistoryAdmin;
import com.example.horseracing2.repositories.PlacedBetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {
    private final PlacedBetRepository placedBetRepository;

    public AdminService(PlacedBetRepository placedBetRepository) {
        this.placedBetRepository = placedBetRepository;
    }

    public List<HistoryAdmin> getAdminHistory() {
        return placedBetRepository.findAll().stream()
                .map(pb -> new HistoryAdmin(
                        pb.getId(),
                        pb.getBet().getId(),
                        pb.getUser().getId(),
                        pb.getSum(),
                        pb.getState(),
                        // припускаю, що в тебе в сутності Bet поле називається multyplier
                        pb.getBet().getMultyplier(),
                        pb.getCreateAt()
                ))
                .collect(Collectors.toList());
    }
}
