package com.example.horseracing2.repositories;

import com.example.horseracing2.entities.PlacedBet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlacedBetRepository extends JpaRepository<PlacedBet, Integer> {}
