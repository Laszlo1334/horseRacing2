package com.example.horseracing2.services;

import com.example.horseracing2.DTO.HistoryAdmin;
import com.example.horseracing2.repositories.PlacedBetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {
    private static final Logger log = LoggerFactory.getLogger(AdminService.class);
    private final PlacedBetRepository placedBetRepository;

    public AdminService(PlacedBetRepository placedBetRepository) {
        this.placedBetRepository = placedBetRepository;
    }

    public List<HistoryAdmin> getAdminHistory() {
        log.info("AdminService.getAdminHistory called");
        var list = placedBetRepository.findAll().stream()
                .map(pb -> new HistoryAdmin(
                        pb.getId(),
                        pb.getBet().getId(),
                        pb.getUser().getId(),
                        pb.getSum(),
                        pb.getState(),
                        pb.getBet().getMultyplier(),
                        pb.getCreateAt()
                ))
                .collect(Collectors.toList());
        log.debug("AdminService returning {} items", list.size());
        return list;
    }
}
