package com.example.planner.model;

public class Goal {
    private String name;
    private String amount;
    private String bal;
    public Goal(String name, String amount, String bal){
        this.name = name;
        this.amount = amount;
        this.bal = bal;
    }

    public String getGoName() {
        return name;
    }
    public String getGoAmount() {
        return "$" + amount;
    }
    public String getBal() {
        return "$" + bal;
    }

    public void setGoName(String name) {
        this.name = name;
    }
    public void setGoAmount(String amount) {
        this.amount = amount;
    }
    public void setBal(String bal) {
        this.bal = bal;
    }
}
