package com.example.horseracing2.Models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bets")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Bet {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "race_id", nullable = false)
    private Race race;

    @ManyToOne
    @JoinColumn(name = "horses_id", nullable = false)
    private Horse horse;

    @Column(precision = 5, scale = 2)
    private BigDecimal multyplier;

    @Column(name = "bet_type", length = 50)
    private String betType;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
