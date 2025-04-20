package com.example.horseracing2.DTO;

public class UpdateBetRequest {
    private int placedBetId;
    private String newState;

    public UpdateBetRequest() {}

    public int getPlacedBetId() {
        return placedBetId;
    }
    public void setPlacedBetId(int placedBetId) {
        this.placedBetId = placedBetId;
    }

    public String getNewState() {
        return newState;
    }
    public void setNewState(String newState) {
        this.newState = newState;
    }
}
