package service;

import model.Restaurant;

import java.util.List;

public interface RestaurantService {

    boolean save(String name, String address, String phoneNumber);

    void delete(String name);

    Restaurant findRestaurantByName(String name);

    List<Restaurant> findAll();

    void assignManagerToRestaurant(String managerEmail, String restaurantName);
}
