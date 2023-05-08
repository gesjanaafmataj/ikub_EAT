package repository;

import model.Menu;
import util.JdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static main.Main.auth;
import static util.Queries.*;

public class MenuRepository {

    public boolean save(Menu menu) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement ps = connection.prepareStatement(INSERT_MENU)) {
            ps.setInt(1, auth.getRestaurantId());
            ps.setString(2, menu.getName());
            ps.setString(3, menu.getDescription());
            ps.setTime(4, menu.getActiveFrom());
            ps.setTime(5, menu.getActiveUntil());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    //Todo: check if menu has products then delete
    public void delete(String name) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement ps = connection.prepareStatement(DELETE_MENU)) {
            ps.setString(1, name);
            ps.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public String findNameOfActiveMenu() {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement ps = connection.prepareStatement(FIND_NAME_OF_ACTIVE_MENU_OF_RESTAURANT)) {
            ps.setInt(1, auth.getRestaurantId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Menu> findAll() {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement ps = connection.prepareStatement(FIND_ALL_MENUS_BY_RESTAURANT)) {
            ps.setInt(1, auth.getRestaurantId());
            ResultSet rs = ps.executeQuery();
            List<Menu> menus = new ArrayList<>();
            while (rs.next()) {
                Menu menu = new Menu();
                menu.setId(rs.getInt("id"));
                menu.setRestaurantId(rs.getInt("restaurant_id"));
                menu.setName(rs.getString("name"));
                menu.setDescription(rs.getString("description"));
                menu.setActiveFrom(rs.getTime("active_from"));
                menu.setActiveUntil(rs.getTime("active_until"));
                menus.add(menu);
            }
            return menus;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Collections.emptyList();
    }
}
