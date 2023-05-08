package service;

import exception.ValueExistsException;
import model.Category;

import java.util.List;

public interface CategoryService {

    boolean save(String name, String description) throws ValueExistsException;

    void deleteByName(String name);

    Category findByName(String name);

    List<Category> findAll();

}
