package com.example.planner.model;

public class Subscriptions {
    private String name;
    private String date;
    private String amount;
    public Subscriptions(String name, String date, String amount){
        this.name = name;
        this.date = date;
        this.amount = amount;
    }

    public String getSubName() {
        return name;
    }
    public String getSubDate() {
        return date;
    }
    public String getSubAmount() {
        return "-$" + amount;
    }
    public void setSubName(String name) {
        this.name = name;
    }
    public void setSubDate(String date) {
        this.date = date;
    }
    public void setSubAmount(String amount) {
        this.amount = amount;
    }
}
