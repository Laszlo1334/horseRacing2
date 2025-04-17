package com.example.horseracing2.Models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "placed_bets")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PlacedBet {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "bet_id", nullable = false)
    private Bet bet;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 20)
    private String state;

    @Column(precision = 10, scale = 2)
    private BigDecimal sum;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
