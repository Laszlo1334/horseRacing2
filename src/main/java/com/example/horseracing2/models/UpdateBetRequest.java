package com.example.horseracing2.models;

import java.math.BigDecimal;

public class UpdateBetRequest {
    private int id;
    private int raceId;
    private int horsesId;
    private BigDecimal multiplier;
    private String betType;

    public UpdateBetRequest() {
    }

    public UpdateBetRequest(int id, int raceId, int horsesId, BigDecimal multiplier, String betType) {
        this.id = id;
        this.raceId = raceId;
        this.horsesId = horsesId;
        this.multiplier = multiplier;
        this.betType = betType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public int getHorsesId() {
        return horsesId;
    }

    public void setHorsesId(int horsesId) {
        this.horsesId = horsesId;
    }

    public BigDecimal getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(BigDecimal multiplier) {
        this.multiplier = multiplier;
    }

    public String getBetType() {
        return betType;
    }

    public void setBetType(String betType) {
        this.betType = betType;
    }
}
