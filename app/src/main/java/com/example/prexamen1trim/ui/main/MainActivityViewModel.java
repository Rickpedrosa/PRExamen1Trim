package com.example.prexamen1trim.ui.main;

import com.example.prexamen1trim.data.Database;
import com.example.prexamen1trim.data.model.Restaurant;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private Database database;
    private float billValue;
    private int tipPercent;
    private int diners;

    public MainActivityViewModel(Database database) {
        this.database = database;
    }

    public float getBillValue() {
        return billValue;
    }

    public void setBillValue(float billValue) {
        this.billValue = billValue;
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

    public float calculateTip() {
        return getBillValue() * (getTipPercent() / 100f);
    }

    public float calculateTotalBill() {
        return calculateTip() + getBillValue();
    }

    public float calculateBillPerDiner() {
        return calculateTotalBill() / getDiners();
    }

    public int calculateRoundedBillPerDiner() {
        return calculateBillPerDiner() != 0 ? (int) (calculateBillPerDiner() + 1) : 0;
    }

    public void addRestaurant(Restaurant restaurant) {
        database.addRestaurant(restaurant);
    }

    public LiveData<List<Restaurant>> getRestaurantsLiveData() {
        return database.getRestaurantLiveData();
    }
}
