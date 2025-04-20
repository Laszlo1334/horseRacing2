package com.example.horseracing2.DTO;

import java.sql.Timestamp;

public class Horse {
    private int id;
    private String name;
    private int age;
    private String breed;
    private String rider;
    private Timestamp createAt;

    public Horse() {
    }

    public Horse(int id, String name, int age, String breed, String rider, Timestamp createAt) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.rider = rider;
        this.createAt = createAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getRider() {
        return rider;
    }

    public void setRider(String rider) {
        this.rider = rider;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }
}
