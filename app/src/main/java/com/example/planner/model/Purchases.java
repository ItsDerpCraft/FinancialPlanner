package com.example.planner.model;

public class Purchases {
    private String name;
    private String date;
    private String amount;
    public Purchases(String name, String date, String amount){
        this.name = name;
        this.date = date;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }
    public String getDate() {
        return date;
    }
    public String getAmount() {
        return "$" + amount;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
}
