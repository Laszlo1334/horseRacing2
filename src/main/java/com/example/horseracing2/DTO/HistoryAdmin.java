package com.example.horseracing2.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class HistoryAdmin {
    private int placedBetId;
    private int betId;
    private int userId;
    private BigDecimal sum;
    private String state;
    private BigDecimal multiplier;
    private Timestamp createAt;

    public HistoryAdmin() {
    }

    public HistoryAdmin(int placedBetId, int betId, int userId, BigDecimal sum,
                        String state, BigDecimal multiplier, Timestamp createAt) {
        this.placedBetId = placedBetId;
        this.betId = betId;
        this.userId = userId;
        this.sum = sum;
        this.state = state;
        this.multiplier = multiplier;
        this.createAt = createAt;
    }

    public int getPlacedBetId() {
        return placedBetId;
    }

    public void setPlacedBetId(int placedBetId) {
        this.placedBetId = placedBetId;
    }

    public int getBetId() {
        return betId;
    }

    public void setBetId(int betId) {
        this.betId = betId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(BigDecimal multiplier) {
        this.multiplier = multiplier;
    }

    @JsonProperty("createdAt")
    public Timestamp getCreateAt() {
        return createAt;
    }

    @JsonProperty("createdAt")
    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }
}
