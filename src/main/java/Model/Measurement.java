package model;

import java.sql.Timestamp;

public class Measurement {
    private Integer aquarium_id;
    private Timestamp time;
    private String Type;
    private Double amount;
    public Measurement(Integer aquarium_id, Timestamp time, String type, Double amount) {
        this.aquarium_id = aquarium_id;
        this.time = time;
        Type = type;
        this.amount = amount;
    }
    public Measurement(Integer aquarium_id, String type, Double amount) {
        this.aquarium_id = aquarium_id;
        Type = type;
        this.amount = amount;
    }
    public Integer getAquarium_id() {
        return aquarium_id;
    }
    public void setAquarium_id(Integer aquarium_id) {
        this.aquarium_id = aquarium_id;
    }
    public Timestamp getTime() {
        return time;
    }
    public void setTime(Timestamp time) {
        this.time = time;
    }
    public String getType() {
        return Type;
    }
    public void setType(String type) {
        Type = type;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
