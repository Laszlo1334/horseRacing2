package com.example.horseracing2.Repositories;

import com.example.horseracing2.Models.PlacedBet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlacedBetRepository extends JpaRepository<PlacedBet, Integer> {
}
