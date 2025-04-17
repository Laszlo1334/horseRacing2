package com.example.horseracing2.Repositories;

import com.example.horseracing2.Models.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRepository extends JpaRepository<Bet, Integer> {
}
