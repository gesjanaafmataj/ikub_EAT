package repository;

import exception.ValueExistsException;
import model.Category;
import util.JdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static util.Queries.*;

public class CategoryRepository {

    public boolean save(Category category) throws ValueExistsException {
        if (doesCategoryExist(category.getName())) {
            throw new ValueExistsException("Category with name " + category.getName() + " already exists!");
        }

        try (Connection connection = JdbcConnection.connect();
             PreparedStatement ps = connection.prepareStatement(INSERT_CATEGORY)) {
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    //Todo: implement category update

    public void deleteByName(String name) {
        if (!doesCategoryHaveProducts(name)) {
            try (Connection connection = JdbcConnection.connect();
                 PreparedStatement ps = connection.prepareStatement(DELETE_CATEGORY_BY_NAME)) {
                ps.setString(1, name);
                ps.execute();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public Category findByName(String name) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement ps = connection.prepareStatement(FIND_CATEGORY_BY_NAME)) {
            ps.setString(1, name);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                Category category = new Category();
                category.setId(result.getInt(1));
                category.setName(result.getString(2));
                category.setDescription(result.getString(3));
                return category;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return new Category();
    }

    public List<Category> findAll() {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement ps = connection.prepareStatement(GET_ALL_CATEGORIES)) {
            ResultSet result = ps.executeQuery();
            List<Category> categories = new ArrayList<>();
            while (result.next()) {
                Category category = new Category();
                category.setId(result.getInt(1));
                category.setName(result.getString(2));
                category.setDescription(result.getString(3));
                categories.add(category);
            }
            return categories;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Collections.emptyList();
    }

    private boolean doesCategoryExist(String categoryName) {
        return checkIfExists(categoryName, FIND_CATEGORY_BY_NAME);
    }

    private boolean doesCategoryHaveProducts(String categoryName) {
        return checkIfExists(categoryName, FIND_PRODUCTS_BY_CATEGORY_NAME);
    }

    private boolean checkIfExists(String categoryName, String query) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, categoryName);
            ResultSet result = ps.executeQuery();
            return result.next();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return true;
    }
}
