package com.example.planner.model;

public class All {
    private String name;
    private String date;
    private String amount;
    public All(String name, String date, String amount){
        this.name = name;
        this.date = date;
        this.amount = amount;
    }

    public String getAllName() {
        return name;
    }
    public String getAllDate() {
        return date;
    }
    public String getAllAmount() {
        return amount;
    }
    public void setAllName(String name) {
        this.name = name;
    }
    public void setAllDate(String date) {
        this.date = date;
    }
    public void setAllAmount(String amount) {
        this.amount = amount;
    }
}
