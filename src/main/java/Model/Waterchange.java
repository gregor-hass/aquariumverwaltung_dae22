package model;

import java.sql.Timestamp;

public class Waterchange {
    private Integer aquarium_id;
    private Timestamp time;
    private Double amount;
    public Waterchange(Integer aquarium_id, Double amount) {
        this.aquarium_id = aquarium_id;
        this.amount = amount;
    }
    public Waterchange(Timestamp time, Double amount) {
        this.time = time;
        this.amount = amount;
    }
    public Waterchange(Integer aquarium_id, Timestamp time, Double amount) {
        this.aquarium_id = aquarium_id;
        this.time = time;
        this.amount = amount;
    }
    public Integer getAquarium_id() {
        return aquarium_id;
    }
    public Timestamp getTime() {
        return time;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAquarium_id(Integer aquarium_id) {
        this.aquarium_id = aquarium_id;
    }
    public void setTime(Timestamp time) {
        this.time = time;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
