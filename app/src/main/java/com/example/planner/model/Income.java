package com.example.planner.model;

public class Income {
    private String name;
    private String date;
    private String amount;
    private String type;
    public Income(String name, String date, String amount, String type){
        this.name = name;
        this.date = date;
        this.amount = amount;
        this.type = type;
    }

    public String getInName() {
        return name;
    }
    public String getInDate() {
        return date;
    }
    public String getInAmount() {
        return "+$" + amount;
    }
    public String getType() {return type;}
    public void setInName(String name) {
        this.name = name;
    }
    public void setInDate(String date) {
        this.date = date;
    }
    public void setInAmount(String amount) {
        this.amount = amount;
    }
    public void setType(String type) {this.type = type;}
}
