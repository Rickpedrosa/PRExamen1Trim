package com.example.prexamen1trim.data;

import com.example.prexamen1trim.data.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class Database {

    private static Database instance;
    private MutableLiveData<List<Restaurant>> restaurantLiveData = new MutableLiveData<>();
    private ArrayList<Restaurant> tipsList = new ArrayList<>();

    private Database() {
        updateRestaurants();
    }

    public static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = new Database();
                }
            }
        }
        return instance;
    }

    public MutableLiveData<List<Restaurant>> getRestaurantLiveData() {
        return restaurantLiveData;
    }

    private void updateRestaurants() {
        restaurantLiveData.setValue(new ArrayList<>(tipsList));
    }

    public void addRestaurant(Restaurant restaurant) {
        tipsList.add(restaurant);
        updateRestaurants();
    }

    public void deleteRestaurant(Restaurant restaurant){
        tipsList.remove(restaurant);
        updateRestaurants();
    }

}
