package service;

import model.Restaurant;
import repository.RestaurantRepository;

import java.util.List;

public class RestaurantServiceImpl implements RestaurantService {

    RestaurantRepository repository = new RestaurantRepository();

    @Override
    public boolean save(String name, String address, String phoneNumber) {
        if (!name.isEmpty()) {
            return repository.save(name, address, phoneNumber);
        } else {
            System.err.println("Name is a required field");
            return false;
        }
    }

    @Override
    public void delete(String name) {
        if (!name.isEmpty()) {
            repository.delete(name);
        } else {
            System.err.println("Name is a required field");
        }
    }

    @Override
    public Restaurant findRestaurantByName(String name) {
        if (!name.isEmpty()) {
            return repository.findRestaurantByName(name);
        } else {
            System.err.println("Name cannot be empty");
            return null;
        }
    }

    @Override
    public List<Restaurant> findAll() {
        return repository.findAll();
    }

    @Override
    public void assignManagerToRestaurant(String managerEmail, String restaurantName) {
        if (!managerEmail.isEmpty() && !restaurantName.isEmpty()) {
            repository.assignManagerToRestaurant(managerEmail, restaurantName);
        } else {
            System.err.println("Required data missing!");
        }
    }
}
