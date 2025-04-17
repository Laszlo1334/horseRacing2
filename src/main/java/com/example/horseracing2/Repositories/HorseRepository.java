package com.example.horseracing2.Repositories;

import com.example.horseracing2.Models.Horse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorseRepository extends JpaRepository<Horse, Integer> {
}
