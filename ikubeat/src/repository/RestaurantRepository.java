package repository;

import exception.ValueExistsException;
import model.Restaurant;
import util.JdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static util.Queries.*;

public class RestaurantRepository {

    public boolean save(String name, String address, String phoneNumber) throws ValueExistsException {
        if (doesRestaurantExists(name)) {
            throw new ValueExistsException("Restaurant with name " + name + " already exists!");
        }

        try (Connection connection = JdbcConnection.connect();
             PreparedStatement ps = connection.prepareStatement(INSERT_RESTAURANT)) {
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, phoneNumber);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void delete(String name) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement ps = connection.prepareStatement(DELETE_RESTAURANT_BY_NAME)) {
            ps.setString(1, name);
            ps.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public Restaurant findRestaurantByName(String name) {
        Restaurant restaurant = new Restaurant();
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement ps = connection.prepareStatement(FIND_RESTAURANT_BY_NAME)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                restaurant.setId(rs.getInt("id"));
                restaurant.setName(rs.getString("name"));
                restaurant.setAddress(rs.getString("address"));
                restaurant.setPhoneNumber(rs.getString("phone_number"));
                restaurant.setUserId(rs.getInt("user_id"));
                return restaurant;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return restaurant;
    }

    public List<Restaurant> findAll() {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement ps = connection.prepareStatement(GET_ALL_RESTAURANTS)) {
            ResultSet rs = ps.executeQuery();
            List<Restaurant> restaurants = new ArrayList<>();
            while (rs.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setId(rs.getInt("id"));
                restaurant.setName(rs.getString("name"));
                restaurant.setAddress(rs.getString("address"));
                restaurant.setPhoneNumber(rs.getString("phone_number"));
                restaurant.setUserId(rs.getInt("user_id"));
                restaurants.add(restaurant);
            }
            return restaurants;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Collections.emptyList();
    }

    public void assignManagerToRestaurant(String email, String restaurantName) throws ValueExistsException {
        if (doesManagerHaveARestaurant(email)) {
            throw new ValueExistsException("Manager with email address " + email + " is already assigned to a restaurant");
        }

        try (Connection connection = JdbcConnection.connect();
             PreparedStatement ps = connection.prepareStatement(ASSIGN_MANAGER_TO_RESTAURANT)) {
            ps.setString(1, email);
            ps.setString(2, restaurantName);
            ps.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private boolean doesRestaurantExists(String name) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement ps = connection.prepareStatement(FIND_RESTAURANT_BY_NAME)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return true;
    }

    private boolean doesManagerHaveARestaurant(String email) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement ps = connection.prepareStatement(COUNT_RESTAURANTS_BY_MANAGER_EMAIL)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int counter = rs.getInt("managerCount");
                return counter != 0;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return true;
    }
}
