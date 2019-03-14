package com.example.prexamen1trim.data.model;

public class Restaurant {

    private String tipDay, restaurantName;
    private float bill;
    private int tipPercent, diners;

    public Restaurant(String tipDay, String restaurantName, float bill, int tipPercent, int diners) {
        this.tipDay = tipDay;
        this.restaurantName = restaurantName;
        this.bill = bill;
        this.tipPercent = tipPercent;
        this.diners = diners;
    }

    public String getTipDay() {
        return tipDay;
    }

    public void setTipDay(String tipDay) {
        this.tipDay = tipDay;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public float getBill() {
        return bill;
    }

    public void setBill(float bill) {
        this.bill = bill;
    }

    public int getTipPercent() {
        return tipPercent;
    }

    public void setTipPercent(int tipPercent) {
        this.tipPercent = tipPercent;
    }

    public int getDiners() {
        return diners;
    }

    public void setDiners(int diners) {
        this.diners = diners;
    }
}
