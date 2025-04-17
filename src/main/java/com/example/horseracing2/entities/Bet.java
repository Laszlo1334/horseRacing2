package com.example.horseracing2.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "bets")
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "race_id", nullable = false)
    private Race race;

    @ManyToOne
    @JoinColumn(name = "horses_id", nullable = false)
    private Horse horse;

    @Column(name = "multyplier", nullable = false)
    private BigDecimal multyplier;

    @Column(name = "bet_type", nullable = false)
    private String betType;

    @Column(name = "create_at", updatable = false)
    private Timestamp createAt;

    public Bet() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Horse getHorse() {
        return horse;
    }

    public void setHorse(Horse horse) {
        this.horse = horse;
    }

    public BigDecimal getMultyplier() {
        return multyplier;
    }

    public void setMultyplier(BigDecimal multyplier) {
        this.multyplier = multyplier;
    }

    public String getBetType() {
        return betType;
    }

    public void setBetType(String betType) {
        this.betType = betType;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }
}
